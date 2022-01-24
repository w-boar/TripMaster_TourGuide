package tourGuide.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.TestProperties;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.LastLocation;
import tourGuide.model.User;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    public void shouldAddUser()  {
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
    public void shouldGetAllUsers()  {
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

	// Tests the simple track user and uses it to test the multithread one
	@Test
	public void shouldTrackUser()  {
        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        InternalTestHelper.setInternalUserNumber(0);
        LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);
        UserService userService = new UserService(gpsUtil, rewardCentral);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation =  localisationService.trackUserLocation(user);
		List<User> users = new ArrayList<User>();
		users.add(user);
		localisationService.trackUserLocation(user);
		userService.tracker.stopTracking();

		assertEquals(user.getUserId(), visitedLocation.userId);// simpleTrackUser test
//		assertEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);
		assertNotEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);// multithreadTrackUser test
	}

@Test
	public void shouldGetAllCurrentLocations()  {
//		GIVEN
GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
    RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
    LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);
    InternalTestHelper.setInternalUserNumber(0);
    UserService userService = new UserService(gpsUtil, rewardCentral);

//		WHEN
	User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
    userService.addUser(user);
    VisitedLocation visitedLocation = localisationService.trackUserLocation(user);
	LastLocation lastLoc = new LastLocation(user.getUserId(), visitedLocation.location);
	List<LastLocation> actualList = userService.getAllCurrentLocations();
	userService.tracker.stopTracking();
//		THEN
	assertEquals(lastLoc.getUserId(), actualList.get(0).getUserId());
    assertEquals(lastLoc.getLastLocation(), actualList.get(0).getLastLocation());
}
//
//	@Ignore // Not yet implemented
	@Test
	public void getNearbyAttractions() {
        GpsUtil gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        RewardCentral rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        UserService userService = new UserService(gpsUtil, rewardCentral);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = localisationService.trackUserLocation(user);

		List<String> attractions = localisationService.getNearByAttractions(user);

		userService.tracker.stopTracking();

		assertEquals(5, attractions.size());
	}

}
