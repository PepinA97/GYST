import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TVShowDatabaseTest {

	@Test
	void testInsertTVShow() {
		TVShowDatabase db = new TVShowDatabase();
		TVShow TVShow1 = new TVShow("The Office", "Comedy", "10", 2005, "Greg Daniels, Ricky Gervais, Stephen Merchant");
		db.Insert(TVShow1);
		assertEquals(db.resultText, "TV show successfully added");		
	}

}
