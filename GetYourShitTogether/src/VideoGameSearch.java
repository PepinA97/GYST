import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoGameSearch {

	private Connection conn;
	protected FilePath fp = new FilePath();
	protected String filePath = fp.getFilePath();
	
	public VideoGameSearch() {
		
	}
	
	public ObservableList<VideoGame> search(String title){
		
		connect();
		ObservableList<VideoGame> searchResults = FXCollections.observableArrayList();
		boolean added = false;
		
		String sql = "SELECT * FROM VideoGames WHERE Title LIKE \"%" + title + "%\"";
		
		try {
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				VideoGame currVideoGame = new VideoGame(rs.getString("Title"), rs.getString("Genre"), rs.getString("Rating"), rs.getInt("Release"), rs.getInt("VGID"), rs.getString("Platform"));
				
				added = searchResults.add(currVideoGame);
				
				if(!added) {
					System.out.println("Error, VideoGame not added properly to the search results");
				} else {
					added = false;
				}
			}
			
			close();
			return searchResults;	
		} catch(SQLException e) {
			close();
			System.out.println(e.getMessage());
		}
		
		return null;		
	}
	
	protected void connect() {
		
		try {
			
			String database = "jdbc:sqlite:" + this.filePath;
			this.conn = DriverManager.getConnection(database);
			
			System.out.println("Connection made");
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database:" + e.getMessage());
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
}
