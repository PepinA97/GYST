import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

class TVShowSearchTest {
	
	private TVShowSearch tvShowTestSearch;

	@Test
	void testAnimeSearch() {
		tvShowTestSearch = new TVShowSearch();
	}

	@Test
	void testSearch() {
		tvShowTestSearch = new TVShowSearch();
		
		ObservableList<TVShow> testList = tvShowTestSearch.search("The Office");

		assertEquals(testList.size(), 27);
	}
}
