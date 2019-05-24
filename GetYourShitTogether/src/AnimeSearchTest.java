import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

class AnimeSearchTest {
	
	private AnimeSearch animeTestSearch;

	@Test
	void testAnimeSearch() {
		animeTestSearch = new AnimeSearch();
	}

	@Test
	void testSearch() {
		animeTestSearch = new AnimeSearch();
		
		ObservableList<Anime> testList = animeTestSearch.search("Jojo");
		
		// should find 7 instances of anime with Jojo in the title
		assertEquals(testList.size(), 7);
	}
}
