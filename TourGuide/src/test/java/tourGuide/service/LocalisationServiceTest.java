package tourGuide.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.User;
import tourGuide.model.UserReward;

public class LocalisationServiceTest {

	@Test
	public void userGetRewards() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());

		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService(gpsUtil, localisationService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsUtil.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
		localisationService.trackUserLocation(user);
		List<UserReward> userRewards = user.getUserRewards();
		userService.tracker.stopTracking();
		assertTrue(userRewards.size() == 1);
	}
	
	@Test
	public void isWithinAttractionProximity() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		Attraction attraction = gpsUtil.getAttractions().get(0);
		assertTrue(localisationService.isWithinAttractionProximity(attraction, attraction));
	}
	
//	@Ignore // Needs fixed - can throw ConcurrentModificationException
	@Test
	public void nearAllAttractions() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		localisationService.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		UserService userService = new UserService(gpsUtil, localisationService);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsUtil.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));


		localisationService.calculateRewards(user);
		List<UserReward> userRewards = userService.getUserRewards(user);
		userService.tracker.stopTracking();

//		assertEquals(26, gpsUtil.getAttractions().size());
		assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
	}

	@Test
	public void getNearbyAttractions() {
		GpsUtil gpsUtil = new GpsUtil();
		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
		InternalTestHelper.setInternalUserNumber(0);
		UserService userService = new UserService(gpsUtil, localisationService);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocation visitedLocation = (VisitedLocation) localisationService.trackUserLocation(user);

		List<String> attractions = localisationService.getNearByAttractions(visitedLocation, user);

		userService.tracker.stopTracking();

		assertEquals(5, attractions.size());
	}
	
}
