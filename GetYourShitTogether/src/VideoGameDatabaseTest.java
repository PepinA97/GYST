import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoGameDatabaseTest {

	@Test
	void testInsertVideoGame() {
		VideoGameDatabase db = new VideoGameDatabase();
		VideoGame VideoGame1 = new VideoGame("Resident Evil 2", "Horror", "9.2", 2019, "PS4, Xbox One, PC");
		db.Insert(VideoGame1);
		assertEquals(db.resultText, "Video game successfully added");		
	}
}
