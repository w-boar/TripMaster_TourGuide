package tourGuide.service;


import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.TestProperties;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocalisationServiceTest {

    private GpsUtil gpsUtil;
    private RewardCentral rewardCentral;
    private LocalisationService localisationService;
    private UserService userService;

    @Test
    public void userGetRewards() {

        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);

        InternalTestHelper.setInternalUserNumber(1);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);
        userService = new UserService(gpsUtil, rewardCentral);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        Attraction attraction = gpsUtil.getAttractions().get(0);
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
        VisitedLocation visitedLocation = localisationService.trackUserLocation(user);

        List<UserReward> userRewards = user.getUserRewards();
        userService.tracker.stopTracking();
        assertTrue(userRewards.size() == 1);
    }


    @Test
    public void isWithinAttractionProximity() {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);
        Attraction attraction = gpsUtil.getAttractions().get(0);
        assertTrue(localisationService.isWithinAttractionProximity(attraction, attraction));
    }

    //	@Ignore // Needs fixed - can throw ConcurrentModificationException
    @Test
    public void nearAllAttractions() throws InterruptedException {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);


        InternalTestHelper.setInternalUserNumber(1);
        userService = new UserService(gpsUtil, rewardCentral);
        User user = userService.getAllUsers().get(0);
        localisationService.setProximityBuffer(Integer.MAX_VALUE);


        localisationService.calculateRewards(user);

        ExecutorService executorService = localisationService.getExecutorService();
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        List<UserReward> userRewards = userService.getUserRewards(userService.getAllUsers().get(0));
        userService.tracker.stopTracking();

        assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
    }


    @Test
    public void shouldMapAttractionsWithDistances() {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);
        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = localisationService.trackUserLocation(user);

        assertNotNull(localisationService.mapAttractionsWithDistances(visitedLocation, user));
    }

    @Test
    public void getUserLocation()  {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);
        userService = new UserService(gpsUtil, rewardCentral);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = localisationService.trackUserLocation(user);
        userService.tracker.stopTracking();
        assertTrue(visitedLocation.userId.equals(user.getUserId()));
    }

    @Test
    public void shouldGetNearbyAttractions() {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);
        userService = new UserService(gpsUtil, rewardCentral);

        InternalTestHelper.setInternalUserNumber(0);


        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = localisationService.trackUserLocation(user);

        List<String> attractions = localisationService.getNearByAttractions(visitedLocation, user);

        userService.tracker.stopTracking();

        assertEquals(5, attractions.size());
    }

}
