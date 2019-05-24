
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class VideoGameRecommendationsTest {

	@Test
	void testVideoGameRecommendations() {
		VideoGameRecommendations yeeehawww= new VideoGameRecommendations();
		//Used when testing sql statement on TVShow table
		assertEquals(yeeehawww.videoGameBacklogSize, 2);
	}
	
	@Test
	void testGetRecommendations() {
		VideoGameRecommendations yeeyourlasthaw = new VideoGameRecommendations();
		yeeyourlasthaw.getRecommendations();
	}
}
