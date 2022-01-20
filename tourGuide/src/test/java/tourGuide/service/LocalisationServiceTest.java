//package tourGuide.service;
//
//import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import tourGuide.helper.InternalTestHelper;
//import tourGuide.model.User;
//import tourGuide.model.UserReward;
//import tourGuide.proxies.gpsUtil.Attraction;
//import tourGuide.proxies.gpsUtil.GpsUtil;
//import tourGuide.proxies.gpsUtil.VisitedLocation;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest
//@EnableDiscoveryClient
//public class LocalisationServiceTest {
//
//	@Autowired
//	GpsUtil gpsUtil;
//
//	LocalisationService localisationService = new LocalisationService();
//
//	@Test
//	public void userGetRewards() {
////		GpsUtil gpsUtil = new GpsUtil();
////		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//
//		InternalTestHelper.setInternalUserNumber(0);
//		UserService userService = new UserService();
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		Attraction attraction = gpsUtil.getAttractions().get(0);
//		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
//		localisationService.trackUserLocation(user);
//		List<UserReward> userRewards = user.getUserRewards();
//		userService.tracker.stopTracking();
//		assertTrue(userRewards.size() == 1);
//	}
//
//	@Test
//	public void isWithinAttractionProximity() {
////		GpsUtil gpsUtil = new GpsUtil();
////		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//		Attraction attraction = gpsUtil.getAttractions().get(0);
//		assertTrue(localisationService.isWithinAttractionProximity(attraction, attraction));
//	}
//
////	@Ignore // Needs fixed - can throw ConcurrentModificationException
//	@Test
//	public void nearAllAttractions() {
////		GpsUtil gpsUtil = new GpsUtil();
//		LocalisationService localisationService = new LocalisationService();
//		localisationService.setProximityBuffer(Integer.MAX_VALUE);
//
//		InternalTestHelper.setInternalUserNumber(1);
//		UserService userService = new UserService();
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		Attraction attraction = gpsUtil.getAttractions().get(0);
//		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
//
//
//		localisationService.calculateRewards(user);
//		List<UserReward> userRewards = userService.getUserRewards(user);
//		userService.tracker.stopTracking();
//
////		assertEquals(26, gpsUtil.getAttractions().size());
//		assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
//	}
//@Ignore
//		@Test
//	public void shouldMapAttractionsWithDistances(){
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
//
//		assertNotNull(localisationService.mapAttractionsWithDistances(visitedLocation, user));
//	}
//@Ignore
//	@Test
//	public void shouldGetNearbyAttractions() {
////		GpsUtil gpsUtil = new GpsUtil();
////		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//		InternalTestHelper.setInternalUserNumber(0);
//		UserService userService = new UserService();
//
//
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);
//
////		List<String> attractions = localisationService.getNearByAttractions(visitedLocation, user);
//
//		userService.tracker.stopTracking();
//
////		assertEquals(5, attractions.size());
//	}
//
//}
