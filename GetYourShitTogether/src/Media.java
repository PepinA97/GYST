import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

abstract class Media {
	
	protected SimpleStringProperty title;
	protected SimpleStringProperty genre;
	protected SimpleStringProperty rating;
	protected SimpleIntegerProperty releaseDate;
	protected int id;
	
	public String getTitle(){
		return this.title.get();
	}
	
	public String getGenre(){
		return this.genre.get();
	}
	
	public String getRating(){
		return this.rating.get();
	}
	
	public int getReleaseDate(){
		return this.releaseDate.get();
	}
	
	public int getID(){
		return this.id;
	}
	
	
}
