import javafx.beans.property.SimpleStringProperty;

public class Anime extends Media{
	
	public Anime(String title, String genre, String rating){
		this.title = new SimpleStringProperty(title);
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.id = -1;
		
	}
	
	public Anime(String title, String genre, String rating, int id){
		this.title = new SimpleStringProperty(title);
		this.genre = new SimpleStringProperty(genre);
		this.rating = new SimpleStringProperty(rating);
		this.id = id;
		
	}
	
}
