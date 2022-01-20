//package tourGuide;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang3.time.StopWatch;
//import org.junit.jupiter.api.Test;
//
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import tourGuide.helper.InternalTestHelper;
//import tourGuide.proxies.gpsUtil.Attraction;
//import tourGuide.proxies.gpsUtil.GpsUtil;
//import tourGuide.proxies.gpsUtil.VisitedLocation;
//import tourGuide.proxies.rewardCentral.RewardCentral;
//import tourGuide.service.LocalisationService;
//import tourGuide.service.UserService;
//import tourGuide.model.User;
//
//@SpringBootTest
//@EnableDiscoveryClient
//public class TestPerformance {
//
//	@Autowired
//	GpsUtil gpsUtil;
//    @Autowired
//	RewardCentral rewardCentral;
//
//	LocalisationService localisationService = new LocalisationService();
//
//	/*
//	 * A note on performance improvements:
//	 *
//	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
//	 *
//	 *     		InternalTestHelper.setInternalUserNumber(100000);
//	 *
//	 *
//	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
//	 *     at the end of the tests remains consistent.
//	 *
//	 *     These are performance metrics that we are trying to hit:
//	 *
//	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
//	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
//     *
//     *     highVolumeGetRewards: 100,000 users within 20 minutes:
//	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
//	 */
//
////	@Ignore
//	@Test
//	public void highVolumeTrackLocation() {
////		GpsUtil gpsUtil = new GpsUtil();
////		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//		// Users should be incremented up to 100,000, and test finishes within 15 minutes
//		InternalTestHelper.setInternalUserNumber(10);
//		UserService userService = new UserService();
//
//		List<User> allUsers = new ArrayList<>();
//		allUsers = userService.getAllUsers();
//
//	    StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
////		for(User user : allUsers) {
////			tourGuideService.trackUserLocation(user);
////		}
//		localisationService.trackUserLocationMultiThread(allUsers);
//		stopWatch.stop();
//		userService.tracker.stopTracking();
//
//		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
//		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
//	}
//
////	@Ignore
//	@Test
//	public void highVolumeGetRewards() {
////		GpsUtil gpsUtil = new GpsUtil();
////		LocalisationService localisationService = new LocalisationService(gpsUtil, new RewardCentral());
//
//		// Users should be incremented up to 100,000, and test finishes within 20 minutes
//		InternalTestHelper.setInternalUserNumber(10);
//		StopWatch stopWatch = new StopWatch();
////		stopWatch.start();
//		UserService userService = new UserService();
//
//	    Attraction attraction = gpsUtil.getAttractions().get(0);
//		List<User> allUsers = new ArrayList<>();
//		allUsers = userService.getAllUsers();
//		allUsers.forEach(u -> u.addToVisitedLocations(new VisitedLocation(u.getUserId(), attraction, new Date())));
//
////	    allUsers.forEach(u -> rewardsService.calculateRewards(u));
//
//		stopWatch.start();
//		localisationService.calculateRewardsMultiThread(allUsers);
//		stopWatch.stop();
//
//		for(User user : allUsers) {
//			assertTrue(user.getUserRewards().size() > 0);
//		}
////		stopWatch.stop();
//		userService.tracker.stopTracking();
//
//		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
//		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
//	}
//
//}
