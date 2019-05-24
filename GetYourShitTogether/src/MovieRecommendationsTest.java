
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class MovieRecommendationsTest {

	@Test
	void testMovieRecommendations() {
		MovieRecommendations yeeehawww= new MovieRecommendations();
		//Used when testing sql statement on anime table
		assertEquals(yeeehawww.movieBacklogSize, 2);
	}
	
	@Test
	void testGetRecommendations() {
		MovieRecommendations yeeyourlasthaw = new MovieRecommendations();
		yeeyourlasthaw.getRecommendations();
	}
}
