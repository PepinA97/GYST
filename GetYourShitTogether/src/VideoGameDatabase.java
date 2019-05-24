import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VideoGameDatabase extends Database<VideoGame> {
	private Connection conn;
	public String resultText = null;

	public VideoGameDatabase () {
		
		super.filePath = filePath;
		connect();
		
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
	
	@Override
	protected void Insert(VideoGame v) {
		resultText = null;
		String title = v.getTitle();
		int release = v.getReleaseDate();
		String genre = v.getGenre();
		String rating = v.getRating();
		String platform = v.getPlatforms();
		platform = platform.substring(1, platform.length()-1);
		
		String sql = "INSERT INTO VideoGames (Title,Release,Genre,Rating,Platform) "
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setInt(2, release);
            stmt.setString(3, genre);
            stmt.setString(4, rating);
            stmt.setString(5,  platform);
            
            stmt.executeUpdate();
            
            System.out.println("Entry added");
            resultText= "Video game successfully added";
                    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		
	}
	
	public int getMaxID() {
		connect();
		int max = -1;
		
		String sql = "SELECT MAX(VGID) FROM VideoGames";
		
		try {
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			
			
		}
		close();
		return max;
	}
}
