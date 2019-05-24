import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VideoGameBacklogItemTest {

	VideoGameBacklogItem game = new VideoGameBacklogItem(0, "Warhammer 2: Vermintide", "Action", "ON HOLD", "9", 1);
	
	@Test
	public void testVideoGameBacklogItem(){
		VideoGameBacklogItem game2 = new VideoGameBacklogItem(1, "Devil May Cry 5", "Action", "PLAYING", "10", 1);

		assertEquals(game2.getTitle(), "Devil May Cry 5");
		assertEquals(game2.getGenre(), "Action");
		assertEquals(game2.getStatus(), "PLAYING");
		assertEquals(game2.getRating(), "10");
		assertEquals(game2.getPriority(), 1);
		assertEquals(game2.getID(), 1);
	}

	@Test
	void testGetTitle() {
		assertEquals(game.getTitle(), "Warhammer 2: Vermintide");
	}

	@Test
	void testGetGenre() {
		assertEquals(game.getGenre(), "Action");
	}

	@Test
	void testGetStatus() {
		assertEquals(game.getStatus(), "ON HOLD");
	}

	@Test
	void testGetRating() {
		assertEquals(game.getRating(), "9");
	}

	@Test
	void testGetPriority() {
		assertEquals(game.getPriority(), 1);
	}

	@Test
	void testGetID() {
		assertEquals(game.getID(), 0);
	}
}
