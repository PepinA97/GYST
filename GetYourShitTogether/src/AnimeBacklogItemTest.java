import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnimeBacklogItemTest {
	
	AnimeBacklogItem anime = new AnimeBacklogItem(0, "Naruto", "Action", "ON HOLD", "9", 1);

	@Test
	void testAnimeBacklogItem() {
		AnimeBacklogItem anime2 = new AnimeBacklogItem(1, "Pokemon", "Action", "WATCHING", "10", 1);

		assertEquals(anime2.getTitle(), "Pokemon");
		assertEquals(anime2.getGenre(), "Action");
		assertEquals(anime2.getStatus(), "WATCHING");
		assertEquals(anime2.getRating(), "10");
		assertEquals(anime2.getPriority(), 1);
		assertEquals(anime2.getID(), 1);
	}

	@Test
	void testGetTitle() {
		assertEquals(anime.getTitle(), "Naruto");
	}

	@Test
	void testGetGenre() {
		assertEquals(anime.getGenre(), "Action");
	}

	@Test
	void testGetStatus() {
		assertEquals(anime.getStatus(), "ON HOLD");
	}

	@Test
	void testGetRating() {
		assertEquals(anime.getRating(), "9");
	}

	@Test
	void testGetPriority() {
		assertEquals(anime.getPriority(), 1);
	}
	
	@Test
	void testGetID() {
		assertEquals(anime.getID(), 0);
	}

}
