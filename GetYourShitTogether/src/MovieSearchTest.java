import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

class MovieSearchTest {

	private MovieSearch movieTestSearch;

	@Test
	void testMovieSearch() {
		movieTestSearch = new MovieSearch();
		
		ObservableList<Movie> testList = movieTestSearch.search("Titanic");
		
		assertEquals(testList.size(), 56);
	}
}
