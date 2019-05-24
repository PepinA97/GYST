import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VideoGame extends Media{

	private SimpleStringProperty platforms;
	
	public VideoGame(String title, String genre, String rating, int releaseDate, String platforms){
		this.title = new SimpleStringProperty(title);
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.releaseDate = new SimpleIntegerProperty(releaseDate);
		this.platforms = new SimpleStringProperty(platforms);
		this.id = -1;
		
	}
	
	public VideoGame(String title, String genre, String rating, int releaseDate, int id, String platforms){
		this.title = new SimpleStringProperty(title);;
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.releaseDate = new SimpleIntegerProperty(releaseDate);
		this.platforms = new SimpleStringProperty(platforms);
		this.id = id;
		
	}
	
	public String getPlatforms() {
		return this.platforms.get();
	}
}
