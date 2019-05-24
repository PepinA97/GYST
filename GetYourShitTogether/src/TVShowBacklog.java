import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TVShowBacklog {

	private Connection conn;
	protected FilePath fp = new FilePath();
	protected String filePath = fp.getFilePath();
	
	public TVShowBacklog () {
		
	}
	
	protected void connect() {
		
		try {
			
			String database = "jdbc:sqlite:" + this.filePath;
			this.conn = DriverManager.getConnection(database);
			
			System.out.println("Connection made");
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to databse:" + e.getMessage());
		}
	}
	
	public void close() {
		try {
			this.conn.close();
			
			System.out.println("Connection closed");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Insert(TVShowBacklogItem t, WatchableMediaStatus s, String userRating, int priority) {
		connect();			
		int id = t.getID();
		
		String sql = "INSERT INTO TVShows_Backlog (TVID,Status,UserRating,Priority) "
                + "VALUES(?,?,?,?)";			
	    try {
	         PreparedStatement stmt = this.conn.prepareStatement(sql);
	         stmt.setInt(1,id);
	            
	         switch(s) {
	           case TO_WATCH: stmt.setString(2, "To Watch"); 
	           break;
	           case WATCHING: stmt.setString(2, "Watching"); 
	           break;
	           case ON_HOLD: stmt.setString(2, "On Hold"); 
	           break;
	           case DROPPED: stmt.setString(2, "Dropped"); 
	           break;
	           case COMPLETED: stmt.setString(2, "Completed");
	           break;
	         };
	            
	        stmt.setString(3, userRating);
	       	
    		//Completed -> "null" priority, don't need priority for a completed item
    		if(s == WatchableMediaStatus.COMPLETED)
    			stmt.setNull(4, java.sql.Types.INTEGER);
    		else
    			 stmt.setInt(4,priority);
	            
	        stmt.executeUpdate();
	            
	        close();
	        System.out.println("Entry added.");
	    } catch (SQLException e) {
	         System.out.println(e.getMessage());
	         close();
	    }
	    
	    
	}
	
	public void Delete(TVShowBacklogItem t) {
		
		connect();
		
		int id = t.getID();
		System.out.println(id);
		
		String sql = "DELETE FROM TVShows_Backlog WHERE TVID = ?";
		
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            
            stmt.executeUpdate();
            
            close();
            
            System.out.println("Entry deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
		
	}

	public void Update(TVShowBacklogItem t) {
		
		connect();
		
		int id = t.getID();
		int priority = t.getPriority();
		String status = t.getStatus();
		String userRating = t.getRating();
		
		String sql = "UPDATE TVShows_Backlog SET Status = ?, UserRating = ?, Priority = ? WHERE TVID = ?";		
		
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1,status);
            stmt.setString(2,userRating);
            
            //Completed -> "null" priority, don't need priority for a completed item
    		if(status == "Completed")
    			stmt.setNull(3, java.sql.Types.INTEGER);
    		else
    			 stmt.setInt(3,priority);
            
    		stmt.setInt(4,id);
            stmt.executeUpdate();
            
            close();
            System.out.println("Entry updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
		
	}
	
	public ObservableList<TVShowBacklogItem> fetchAll() {
		
		connect();
		
		 String sql = "SELECT TVShows.TVID,Title,Genre,Status,UserRating,Priority FROM TVShows JOIN TVShows_backlog ON TVShows.TVID IS TVShows_backlog.TVID";
	        
	        ObservableList<TVShowBacklogItem> result = FXCollections.observableArrayList();
	        boolean added = false;
	        
	        try {
	            Statement stmt = this.conn.createStatement();
	            
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            while(rs.next()) {
	            	TVShowBacklogItem currTVShow = new TVShowBacklogItem(rs.getInt("TVID"),rs.getString("Title"),rs.getString("Genre"),rs.getString("Status"),rs.getString("UserRating"),rs.getInt("Priority"));
	            	
	            	added = result.add(currTVShow);
	            	
	            	if(!added){
	            		System.out.println("Error, tvshow not added properly");
	            	}else {
	            		added = false;
	            	}
	            }
	            
	            close();
	            return result;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            close();
	        }
	        
	        return null;
		
	}
}
