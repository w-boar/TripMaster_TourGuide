package tourGuide.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.TestProperties;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.Location;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.proxies.tripPricer.TripPricer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TripPricerServiceTest {

    @Test
    public void shouldGetTripDeals() {
        TripPricer tripPricer = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.tripPricer.TripPricer.class, TestProperties.tripPricerSocket);
        TripPricerService tripPricerService = new TripPricerService(tripPricer);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        List<Provider> providers = tripPricerService.getTripDeals(user);

        assertEquals(5, providers.size());
    }
    @Test
    public void shouldReturnCumulativeRewardPoints() {
        TripPricer tripPricer = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.tripPricer.TripPricer.class, TestProperties.tripPricerSocket);
        TripPricerService tripPricerService = new TripPricerService(tripPricer);
        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");

        assertEquals(0,user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum());

        Location loc=new Location(26.890959D, -80.116577D);
        Location loc2=new Location(26.890650D, -80.116700D);
        Date date = new Date();
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), loc, date);
        VisitedLocation visitedLocation2 = new VisitedLocation(UUID.randomUUID(), loc2, date);
        Attraction attraction = new Attraction("Union Station", "Washington D.C.", "CA", 38.897095D, -77.006332D);
        Attraction attraction2 = new Attraction("break Station", "Washington D.C.", "CA", 38.89189D, -77.001515D);

        UserReward userReward = new UserReward(visitedLocation, attraction, 10);
        UserReward userReward2 = new UserReward(visitedLocation2, attraction2, 10);
        user.addUserReward(userReward);
        user.addUserReward(userReward2);

        assertEquals(20,user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum());
}
}