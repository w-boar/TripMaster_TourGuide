package tourGuide.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.TestProperties;
import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.proxies.tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TripPricerServiceTest {

    @Test
    public void getTripDeals() {
        TripPricer tripPricer = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.tripPricer.TripPricer.class, TestProperties.tripPricerSocket);
        TripPricerService tripPricerService = new TripPricerService(tripPricer);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        List<Provider> providers = tripPricerService.getTripDeals(user);

        assertEquals(5, providers.size());
    }

}