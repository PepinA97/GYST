import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

class VideoGameSearchTest {
	
	private VideoGameSearch vidyaTestSearch;

	@Test
	void testAnimeSearch() {
		vidyaTestSearch = new VideoGameSearch();
	}

	@Test
	void testSearch() {
		vidyaTestSearch = new VideoGameSearch();
		
		ObservableList<VideoGame> testList = vidyaTestSearch.search("Apex");

		assertEquals(testList.size(), 1);
	}
}
