package tourGuide;


import feign.Feign;
import feign.gson.GsonDecoder;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.helper.InternalTestHelper;
import tourGuide.model.User;
import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.rewardCentral.RewardCentral;
import tourGuide.service.LocalisationService;
import tourGuide.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestPerformance {

    private GpsUtil gpsUtil;
    private RewardCentral rewardCentral;
    private LocalisationService localisationService;
    private UserService userService;

    /*
     * A note on performance improvements:
     *
     *     The number of users generated for the high volume tests can be easily adjusted via this method:
     *
     *     		InternalTestHelper.setInternalUserNumber(100000);
     *
     *
     *     These tests can be modified to suit new solutions, just as long as the performance metrics
     *     at the end of the tests remains consistent.
     *
     *     These are performance metrics that we are trying to hit:
     *
     *     highVolumeTrackLocation: 100,000 users within 15 minutes:
     *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
     *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     */

    @Test
    public void highVolumeTrackLocation() throws InterruptedException {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);

        //Users should be incremented up to 100,000, and test finishes within 15 minutes
        InternalTestHelper.setInternalUserNumber(100000);

        userService = new UserService(gpsUtil, rewardCentral);
        List<User> allUsers = userService.getAllUsers();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        localisationService.trackUserLocationMultiThread(allUsers);
        stopWatch.stop();
        userService.tracker.stopTracking();

        System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }


//    @Test
//    public void exp3_should_throw_exception_when_calculating_square_root_of_negative_number(){
//        catchException(calculator).squareRoot(-10);
//
//        assert caughtException() instanceof IllegalArgumentException;
//    }


    @Test
    public void highVolumeGetRewards() {
        gpsUtil = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class, TestProperties.gpsUtilSocket);
        rewardCentral = Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class, TestProperties.rewardCentralSocket);
        localisationService = new LocalisationService(gpsUtil, rewardCentral);

        // Users should be incremented up to 100,000, and test finishes within 20 minutes
        InternalTestHelper.setInternalUserNumber(100000);
        StopWatch stopWatch = new StopWatch();

        UserService userService = new UserService(gpsUtil, rewardCentral);

        Attraction attraction = gpsUtil.getAttractions().get(0);
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(u -> u.addToVisitedLocations(new VisitedLocation(u.getUserId(), attraction, new Date())));


        stopWatch.start();
        localisationService.calculateRewardsMultiThread(allUsers);
        stopWatch.stop();

        for (User user : allUsers) {
            assertTrue(user.getUserRewards().size() > 0);
        }

        userService.tracker.stopTracking();

        System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }

}
