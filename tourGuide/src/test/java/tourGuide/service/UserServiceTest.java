package tourGuide.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.TestProperties;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.User;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class UserServiceTest {


    @Test
    public void shouldGetUserLocation() {
        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);
        UserService userService = new UserService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = localisationService.trackUserLocation(user);
        userService.tracker.stopTracking();
        assertTrue(visitedLocation.userId.equals(user.getUserId()));
    }

    @Test
    public void shouldAddUser() {
        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        InternalTestHelper.setInternalUserNumber(0);
        UserService userService = new UserService(gpsUtil, rewardCentral);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

        userService.addUser(user);
        userService.addUser(user2);

        User retrivedUser = userService.getUser(user.getUserName());
        User retrivedUser2 = userService.getUser(user2.getUserName());

        userService.tracker.stopTracking();

        assertEquals(user, retrivedUser);
        assertEquals(user2, retrivedUser2);
    }

    @Test
    public void shouldGetAllUsers() {
        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        InternalTestHelper.setInternalUserNumber(0);
        UserService userService = new UserService(gpsUtil, rewardCentral);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

        userService.addUser(user);
        userService.addUser(user2);

        List<User> allUsers = userService.getAllUsers();

        userService.tracker.stopTracking();

        assertTrue(allUsers.contains(user));
        assertTrue(allUsers.contains(user2));
    }

//	// Tests the simple track user and uses it to test the multithread one
//	@Test
//	public void trackUser() throws ExecutionException, InterruptedException {
//        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
//        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
//        InternalTestHelper.setInternalUserNumber(0);
//        LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);
//        UserService userService = new UserService(gpsUtil, rewardCentral);
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		VisitedLocation visitedLocation =  localisationService.trackUserLocation(user).get();
//		List<User> users = new ArrayList<User>();
//		users.add(user);
//		localisationService.trackUserLocation(user);
//		userService.tracker.stopTracking();
//
////		assertEquals(user.getUserId(), visitedLocation.userId);// simpleTrackUser test
////		assertEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);
//		assertNotEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);// multithreadTrackUser test
//	}

//@Test
//	public void shouldGetAllCurrentLocations(){
////		GIVEN
////	GpsUtil gpsUtil = new GpsUtil();
////	LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//	InternalTestHelper.setInternalUserNumber(0);
//	UserService userService = new UserService();
//
////		WHEN
//	User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//	VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
//	userService.addUser(user);
//	List<String> expectedList = new ArrayList<String>();
//	expectedList.add(user.getUserId() + ": " + "{longitude: " + user.getLastVisitedLocation().getLocation().getLongitude()+", latitude: "+ user.getLastVisitedLocation().getLocation().getLatitude() + "}");
//	List<String> actualList = userService.getAllCurrentLocations();
//	userService.tracker.stopTracking();
////		THEN
//	assertEquals(expectedList, actualList);
//}
//
////	@Ignore // Not yet implemented
////	@Test
////	public void getNearbyAttractions() {
////		GpsUtil gpsUtil = new GpsUtil();
////		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
////		InternalTestHelper.setInternalUserNumber(0);
////		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
////
////		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
////		VisitedLocation visitedLocation = tourGuideService.trackUserLocation(user);
////
////		List<String> attractions = tourGuideService.getNearByAttractions(visitedLocation, user);
////
////		tourGuideService.tracker.stopTracking();
////
////		assertEquals(5, attractions.size());
////	}
//
////	@Test
////	public void getTripDeals() {
////		GpsUtil gpsUtil = new GpsUtil();
////		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
////		InternalTestHelper.setInternalUserNumber(0);
////		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
////
////		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//////		user.getUserPreferences().setTripDuration(2);
////		List<Provider> providers = tourGuideService.getTripDeals(user);
////
////		tourGuideService.tracker.stopTracking();
////
//////		assertNotNull(user.getUserPreferences());
//////		assertNotNull(user.getUserPreferences().getTripDuration());
//////		assertEquals(2, user.getUserPreferences().getTripDuration());
////		assertEquals(5, providers.size());
////	}
//
//
}
