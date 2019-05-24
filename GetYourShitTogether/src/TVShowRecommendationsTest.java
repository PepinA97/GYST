
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class TVShowRecommendationsTest {

	@Test
	void testTVShowRecommendations() {
		TVShowRecommendations yeeehawww= new TVShowRecommendations();
		//Used when testing sql statement on TVShow table
		assertEquals(yeeehawww.tvShowBacklogSize, 2);
	}
	
	@Test
	void testGetRecommendations() {
		TVShowRecommendations yeeyourlasthaw = new TVShowRecommendations();
		yeeyourlasthaw.getRecommendations();
	}
}
