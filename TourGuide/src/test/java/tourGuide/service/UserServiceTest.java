package tourGuide.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;
import org.junit.Test;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserServiceTest {

	@Test
	public void getUserLocation() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService(gpsUtil, localisationService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
		userService.tracker.stopTracking();
		assertTrue(visitedLocation.userId.equals(user.getUserId()));
	}
	
	@Test
	public void addUser() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService(gpsUtil, localisationService);
		
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
	public void getAllUsers() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService( gpsUtil, localisationService);
		
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
	public void trackUser() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService( gpsUtil, localisationService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
		List<User> users = new ArrayList<User>();
		users.add(user);
		localisationService.trackUserLocationMultiThread(users);
		userService.tracker.stopTracking();

		assertEquals(user.getUserId(), visitedLocation.userId);// simpleTrackUser test
//		assertEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);
		assertNotEquals(visitedLocation.timeVisited, user.getLastVisitedLocation().timeVisited);// multithreadTrackUser test
	}

@Test
	public void shouldGetAllCurrentLocations(){
//		GIVEN
	GpsUtil gpsUtil = new GpsUtil();
	LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
	InternalTestHelper.setInternalUserNumber(0);
	UserService userService = new UserService(gpsUtil, localisationService);

//		WHEN
	User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
	VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
	userService.addUser(user);
	List<String> expectedList = new ArrayList<String>();
	expectedList.add(user.getUserId() + ": " + "{longitude: " + user.getLastVisitedLocation().location.longitude+", latitude: "+ user.getLastVisitedLocation().location.latitude + "}");
	List<String> actualList = userService.getAllCurrentLocations();
	userService.tracker.stopTracking();
//		THEN
	assertEquals(expectedList, actualList);
}

//	@Ignore // Not yet implemented
//	@Test
//	public void getNearbyAttractions() {
//		GpsUtil gpsUtil = new GpsUtil();
//		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
//		InternalTestHelper.setInternalUserNumber(0);
//		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		VisitedLocation visitedLocation = tourGuideService.trackUserLocation(user);
//
//		List<String> attractions = tourGuideService.getNearByAttractions(visitedLocation, user);
//
//		tourGuideService.tracker.stopTracking();
//
//		assertEquals(5, attractions.size());
//	}

//	@Test
//	public void getTripDeals() {
//		GpsUtil gpsUtil = new GpsUtil();
//		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
//		InternalTestHelper.setInternalUserNumber(0);
//		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
////		user.getUserPreferences().setTripDuration(2);
//		List<Provider> providers = tourGuideService.getTripDeals(user);
//
//		tourGuideService.tracker.stopTracking();
//
////		assertNotNull(user.getUserPreferences());
////		assertNotNull(user.getUserPreferences().getTripDuration());
////		assertEquals(2, user.getUserPreferences().getTripDuration());
//		assertEquals(5, providers.size());
//	}
	
	
}