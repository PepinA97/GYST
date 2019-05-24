import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie extends Media {
	
	private SimpleStringProperty directors;
	
	public Movie(String title, String genre, String rating, int releaseDate, String directors){
		this.title = new SimpleStringProperty(title);;
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.releaseDate = new SimpleIntegerProperty(releaseDate);
		this.directors = new SimpleStringProperty(directors);
		this.id = -1;
		
	}
	
	public Movie(String title, String genre, String rating, int releaseDate, int id, String directors){
		this.title = new SimpleStringProperty(title);;
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.releaseDate = new SimpleIntegerProperty(releaseDate);
		this.directors = new SimpleStringProperty(directors);
		this.id = id;
	}
	
	public String getDirectors()
	{
		return this.directors.get();
	}
	
}
