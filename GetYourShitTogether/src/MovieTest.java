import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MovieTest {

	Movie testFilm = new Movie("Detective Pikachu", "Mystery", "8", 2019, "Rob Letterman");
	
	String directors = "Rob Letterman";
	String directors2 = "Anthony Russo, Joe Russo";
	
	@Test
	void testMovie() {
		Movie testFilm2 = new Movie("Avengers: Endgame", "Action", "9.5", 2019, "Anthony Russo, Joe Russo");
		assertEquals(testFilm2.getTitle(), "Avengers: Endgame");
		assertEquals(testFilm2.getGenre(), "Action");
		assertEquals(testFilm2.getRating(), "9.5");
		assertEquals(testFilm2.getReleaseDate(), 2019);
		assertEquals(testFilm2.getID(), -1);
		assertEquals(testFilm2.getDirectors(), directors2);
	}

	@Test
	void testGetDirector() {
		assertEquals(testFilm.getDirectors(), directors);
	}

	@Test
	void testGetTitle() {
		assertEquals(testFilm.getTitle(), "Detective Pikachu");
	}

	@Test
	void testGetGenre() {
		assertEquals(testFilm.getGenre(), "Mystery");
	}

	@Test
	void testGetRating() {
		assertEquals(testFilm.getRating(), "8");
	}

	@Test
	void testGetReleaseDate() {
		assertEquals(testFilm.getReleaseDate(), 2019);
	}

	@Test
	void testGetID() {
		assertEquals(testFilm.getID(), -1);
	}

}
