import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class AnimeRecommendationsTest {

	@Test
	void testAnimeRecommendations() {
		AnimeRecommendations yeeehawww= new AnimeRecommendations();
		//Used when testing sql statement on anime table
		assertEquals(yeeehawww.animeBacklogSize, 2);
	}
	
	@Test
	void testGetRecommendations() {
		AnimeRecommendations yeeyourlasthaw = new AnimeRecommendations();
		yeeyourlasthaw.getRecommendations();
	}

}
