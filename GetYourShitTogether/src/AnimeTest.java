import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnimeTest {

	Anime anime1 = new Anime("Jojo's Bizarre Adventure", "Shonen", "10");
	
	
	
	@Test
	void testAnime() {
		
		Anime anime2 = new Anime("Tengen Toppa Gurren Lagann", "Sci-fi", "10");
		assertEquals(anime2.getTitle(), "Tengen Toppa Gurren Lagann");
		assertEquals(anime2.getGenre(), "Sci-fi");
		assertEquals(anime2.getRating(), "10");
		assertEquals(anime2.getID(), -1);
	}

	@Test
	void testGetTitle() {
		assertEquals(anime1.getTitle(), "Jojo's Bizarre Adventure");
	}

	@Test
	void testGetGenre() {
		assertEquals(anime1.getGenre(), "Shonen");
	}

	@Test
	void testGetRating() {
		assertEquals(anime1.getRating(), "10");
	}

	@Test
	void testGetID() {
		assertEquals(anime1.getID(), -1);
	}

}
