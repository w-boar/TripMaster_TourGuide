package TripPricer;

import org.junit.jupiter.api.Test;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TripPricerTest {

    private TripPricerService tripPricerService = new TripPricerService();
    private TripPricerController tripPricerController = new TripPricerController(tripPricerService);


    @Test
    public void shouldReturnListOfProviders() {
        int tripDuration = 3;
        int numberOfAdults = 2;
        int numberOfChildren = 0;


        List<Provider> providers = tripPricerController.getPrice("", UUID.randomUUID(), numberOfAdults,
                numberOfChildren, tripDuration, 10);

        assertEquals(5, providers.size());
    }

    @Test
    public void shouldReturnARandomProviderName() {
        String provider = tripPricerController.getProviderName("", 0);

        assertNotNull(provider);
    }

}
