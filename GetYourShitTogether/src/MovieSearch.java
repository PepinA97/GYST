import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieSearch {
	
	private Connection conn;
	protected FilePath fp = new FilePath();
	protected String filePath = fp.getFilePath();
	
	public MovieSearch() {
		
	}
	
	public ObservableList<Movie> search(String title){
		
		connect();
		ObservableList<Movie> searchResults = FXCollections.observableArrayList();
		boolean added = false;
		
		String sql = "SELECT * FROM Movies WHERE Title LIKE \"%" + title + "%\"";
		
		try {
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Movie currMovie = new Movie(rs.getString("Title"), rs.getString("Genre"), rs.getString("Rating"), rs.getInt("Release"), rs.getInt("MovieID"), rs.getString("Director"));
				
				added = searchResults.add(currMovie);
				
				if(!added) {
					System.out.println("Error, Movie not added properly to the search results");
				} else {
					added = false;
				}
			}
			
			close();
		} catch(SQLException e) {
			close();
			System.out.println(e.getMessage());
		}
		
		return searchResults;		
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
