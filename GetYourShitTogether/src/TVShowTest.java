import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TVShowTest {
	
	String creators = "Greg Daniels, Ricky Gervais, Stephen Merchant";
	String creators2 = "David Benioff, D.B. Weiss";

	TVShow show = new TVShow("The Office", "Comedy", "10", 2005, creators);
	
	@Test
	void testTVShow() {
		TVShow show2 = new TVShow("Game of Thrones", "Fantasy", "10", 2011, creators2);
		assertEquals(show2.getTitle(), "Game of Thrones");
		assertEquals(show2.getGenre(), "Fantasy");
		assertEquals(show2.getRating(), "10");
		assertEquals(show2.getReleaseDate(), 2011);
		assertEquals(show2.getDirectors(), creators2);
		assertEquals(show2.getID(), -1);
		
	}

	@Test
	void testGetCreators() {
		assertEquals(show.getDirectors(),creators);
	}

	@Test
	void testGetTitle() {
		assertEquals(show.getTitle(), "The Office");
	}

	@Test
	void testGetGenre() {
		assertEquals(show.getGenre(), "Comedy");
	}

	@Test
	void testGetRating() {
		assertEquals(show.getRating(), "10");
	}

	@Test
	void testGetReleaseDate() {
		assertEquals(show.getReleaseDate(), 2005);
	}

	@Test
	void testGetID() {
		assertEquals(show.getID(), -1);
	}

}
