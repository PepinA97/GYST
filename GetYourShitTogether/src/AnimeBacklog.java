import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnimeBacklog {

	private Connection conn;
	protected FilePath fp = new FilePath();
	protected String filePath = fp.getFilePath();
	public String resultText = null;
	
	public AnimeBacklog () {
		
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
	
	public void Insert(AnimeBacklogItem a, WatchableMediaStatus s, String userRating, int priority) {
		connect();
		resultText = null;
		int id = a.getID();
		
		String sql = "INSERT INTO Anime_Backlog (AnimeID,Status,UserRating,Priority) "
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
            
            System.out.println("Entry added.");
            resultText = "Anime successfully added to the backlog.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
			
		
	}
	
	public void Delete(AnimeBacklogItem a) {
		resultText = null;
		int id = a.getID();
		
		connect();
		
		String sql = "DELETE FROM Anime_Backlog WHERE AnimeID = ?";
		
		
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            
            stmt.executeUpdate();
            
            close();
            
            resultText = "Anime successfully deleted from the backlog";
            System.out.println("Entry deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
		
		
	}

	public void Update(AnimeBacklogItem a) {
		connect();
		resultText = null;
		int id = a.getID();
		String userRating = a.getRating();
		String status = a.getStatus();
		int priority = a.getPriority();
		
		String sql = "UPDATE Anime_Backlog SET Status = ?, UserRating = ?, Priority = ? WHERE AnimeID = ?";
		
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
            resultText = "Anime updated successfully";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	        close();
        }
	}
	
	 public ObservableList<AnimeBacklogItem> fetchAll() {
			
		 connect();
		 String sql = "SELECT Anime.AnimeID,Title,Genre,Status,UserRating,Priority FROM Anime JOIN Anime_backlog ON Anime.AnimeID IS Anime_backlog.AnimeID";
	        
	        ObservableList<AnimeBacklogItem> result = FXCollections.observableArrayList();
	        boolean added = false;
	        
	        try {
	            Statement stmt = this.conn.createStatement();
	            
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            while(rs.next()) {
	            	AnimeBacklogItem currAnime = new AnimeBacklogItem(rs.getInt("AnimeID"),rs.getString("Title"),rs.getString("Genre"),rs.getString("Status"),rs.getString("UserRating"),rs.getInt("Priority"));
	            	
	            	added = result.add(currAnime);
	            	
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
