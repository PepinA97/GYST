import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoGameTest {
	
	VideoGame game = new VideoGame("Devil May Cry 5", "Action", "9.7", 2019, "PS4, Xbox One, PC");
	String platforms = "PS4, Xbox One, PC";

	@Test
	void testVideoGame() {
		VideoGame game2 = new VideoGame("Resident Evil 2", "Horror", "9.2", 2019, "PS4, Xbox One, PC");
		
		assertEquals(game2.getTitle(), "Resident Evil 2");
		assertEquals(game2.getGenre(), "Horror");
		assertEquals(game2.getRating(), "9.2");
		assertEquals(game2.getReleaseDate(), 2019);
		assertEquals(game2.getPlatforms(), platforms);
	}

	@Test
	void testGetPlatforms() {
		assertEquals(game.getPlatforms(), platforms);
	}

	@Test
	void testGetTitle() {
		assertEquals(game.getTitle(), "Devil May Cry 5");
	}

	@Test
	void testGetGenre() {
		assertEquals(game.getGenre(), "Action");
	}

	@Test
	void testGetRating() {
		assertEquals(game.getRating(), "9.7");
	}

	@Test
	void testGetReleaseDate() {
		assertEquals(game.getReleaseDate(), 2019);
	}

	@Test
	void testGetID() {
		assertEquals(game.getID(), -1);
	}

}
