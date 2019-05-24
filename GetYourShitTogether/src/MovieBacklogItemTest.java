import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieBacklogItemTest {
	
	MovieBacklogItem movie = new MovieBacklogItem(0, "Snatch", "Comedy", "ON HOLD", "9", 1);

	@Test
	void testMovieBacklogItem() {
		MovieBacklogItem movie2 = new MovieBacklogItem(1, "Hot Fuzz", "Comedy", "WATCHING", "10", 1);

		assertEquals(movie2.getTitle(), "Hot Fuzz");
		assertEquals(movie2.getGenre(), "Comedy");
		assertEquals(movie2.getStatus(), "WATCHING");
		assertEquals(movie2.getRating(), "10");
		assertEquals(movie2.getPriority(), 1);
		assertEquals(movie2.getID(), 1);
	}

	@Test
	void testGetTitle() {
		assertEquals(movie.getTitle(), "Snatch");
	}

	@Test
	void testGetGenre() {
		assertEquals(movie.getGenre(), "Comedy");
	}

	@Test
	void testGetStatus() {
		assertEquals(movie.getStatus(), "ON HOLD");
	}

	@Test
	void testGetRating() {
		assertEquals(movie.getRating(), "9");
	}

	@Test
	void testGetPriority() {
		assertEquals(movie.getPriority(), 1);
	}

	@Test
	void testGetID() {
		assertEquals(movie.getID(), 0);
	}
}
