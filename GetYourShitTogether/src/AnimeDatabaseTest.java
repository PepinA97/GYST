import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AnimeDatabaseTest {
	
	@Test
	void testInsertAnime() {
		AnimeDatabase db = new AnimeDatabase();
		Anime anime1 = new Anime("Jojo's Bizarre Adventure", "Shonen", "10");
		db.Insert(anime1);
		assertEquals(db.resultText, "Anime successfully added.");		
	}

}
