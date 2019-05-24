import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoGameBacklog {

	private Connection conn;
	protected FilePath fp = new FilePath();
	protected String filePath = fp.getFilePath();
	
	public VideoGameBacklog () {
		
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
	
	public void Insert(VideoGameBacklogItem v, VideoGameStatus s, String userRating, int priority) {
		connect();
		int id = v.getID();
		
		String sql = "INSERT INTO VideoGames_Backlog (VGID,Status,UserRating,Priority) "
                + "VALUES(?,?,?,?)";			
		try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            
            switch(s) {
            	case TO_PLAY: stmt.setString(2, "To Play"); 
            	break;
            	case PLAYING: stmt.setString(2, "Playing"); 
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
    		if(s == VideoGameStatus.COMPLETED)
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
	
	public void Delete(VideoGameBacklogItem v) {
		
		this.connect();

		int id = v.getID();
		
		String sql = "DELETE FROM VideoGames_Backlog WHERE VGID = ?";
		
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

	public void Update(VideoGameBacklogItem v) {
		
		connect();
		int id = v.getID();
		int priority = v.getPriority();
		String status = v.getStatus();
		String userRating = v.getRating();
		
		
		String sql = "UPDATE VideoGames_Backlog SET Status = ?, UserRating = ?, Priority = ? WHERE VGID = ?";
		
		
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
	
	public ObservableList<VideoGameBacklogItem> fetchAll() {
		
		connect();
		
		 String sql = "SELECT VideoGames.VGID,Title,Genre,Status,UserRating,Priority FROM VideoGames JOIN VideoGames_backlog ON VideoGames.VGID IS VideoGames_backlog.VGID";
	        
	        ObservableList<VideoGameBacklogItem> result = FXCollections.observableArrayList();
	        boolean added = false;
	        
	        try {
	            Statement stmt = this.conn.createStatement();
	            
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            while(rs.next()) {
	            	VideoGameBacklogItem currVG = new VideoGameBacklogItem(rs.getInt("VGID"),rs.getString("Title"),rs.getString("Genre"),rs.getString("Status"),rs.getString("UserRating"),rs.getInt("Priority"));
	            	
	            	added = result.add(currVG);
	            	
	            	if(!added){
	            		System.out.println("Error, videogame not added properly");
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
