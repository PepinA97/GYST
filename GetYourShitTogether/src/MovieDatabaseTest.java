import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieDatabaseTest {

	@Test
	void testInsertMovie() {
		MovieDatabase db = new MovieDatabase();
		Movie Movie1 = new Movie("Detective Pikachu", "Mystery", "8", 2019, "Rob Letterman");
		db.Insert(Movie1);
		assertEquals(db.resultText, "Movie successfully added");		
	}

}
