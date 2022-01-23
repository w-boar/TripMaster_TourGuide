package tourGuide.service;


import org.springframework.stereotype.Service;
import tourGuide.model.NearbyAttraction;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.gpsUtil.Location;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The type Localisation service.
 */
@Service
public class LocalisationService {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    private GpsUtil gpsUtil;
    private RewardCentral rewardCentral;
    private int defaultProximityBuffer = 10;
    private int proximityBuffer = defaultProximityBuffer;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10000);

    /**
     * Instantiates a new LocalisationService.
     *
     * @param gpsUtil
     * @param rewardCentral
     */
    public LocalisationService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
        this.gpsUtil = gpsUtil;
        this.rewardCentral = rewardCentral;
    }

    /**
     * Gets user's location
     *
     * @param user
     * @return visited location
     */
    public VisitedLocation getUserLocation(User user) {
        VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
                user.getLastVisitedLocation() :
                trackUserLocation(user);
        return visitedLocation;
    }

    /**
     * Gets map of attractions sorted by distance from a visited location
     *
     * @param visitedLocation
     * @return tree map of attractions as value with their distances from user's visitedLocation as key
     */
    public TreeMap<Double, Attraction> mapAttractionsWithDistances(VisitedLocation visitedLocation) {
        List<Attraction> attractions = gpsUtil.getAttractions();
        TreeMap<Double, Attraction> attractionsMap = new TreeMap<>();
        for (Attraction attraction : attractions) {
            attractionsMap.put(getDistance(visitedLocation.location, attraction), attraction);
        }
        return attractionsMap;
    }

    /**
     * Gets lists of attractions' descriptions
     *
     * @param user
     * @return tree map of attractions as value with their distances from user's visitedLocation as key
     */
    public List<String> getNearByAttractions(User user) {
        List<String> nearbyAttractions = new ArrayList<>();
        VisitedLocation visitedLocation = getUserLocation(user);
        TreeMap<Double, Attraction> attractionsMap = mapAttractionsWithDistances(visitedLocation);

        Set<Double> keys = attractionsMap.keySet();
        int i = 0;
        for (double key : keys) {
            if (i < 5) {
                Attraction attraction = attractionsMap.get(key);
                NearbyAttraction nearbyAttraction = new NearbyAttraction(attraction.getLatitude(),
                        attraction.getLongitude(), attraction, user, visitedLocation, getDistance(visitedLocation.location, attraction),
                        rewardCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId()));
                nearbyAttractions.add(nearbyAttraction.toString());
                i++;
            }
        }
        return nearbyAttractions;
    }

    /**
     * Gets an updated user's location
     *
     * @param user
     * @return visited location
     */
    public VisitedLocation trackUserLocation(User user) {
        VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        calculateRewards(user);
        return visitedLocation;
    }

    /**
     * Gets an updated location for each user of a given list
     *
     * @param userList
     */
    public void trackUserLocationMultiThread(List<User> userList) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        List<Future<?>> listFuture = new ArrayList<>();

        for (User user : userList) {
            Future<?> future = executorService.submit(() -> {

                VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
                user.addToVisitedLocations(visitedLocation);
            });

            listFuture.add(future);
        }

        listFuture.stream().forEach(futureResult -> {
            try {
                futureResult.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                ;
            }
        });

        executorService.shutdown();

        calculateRewardsMultiThread(userList);

    }

    /**
     * Gets an executor service to manage the multi thread
     *
     * @return executorService
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Gets user's rewards updated
     *
     * @param user
     */
    public void calculateRewards(User user) {
        List<VisitedLocation> userLocations = user.getVisitedLocations();
        List<Attraction> attractions = gpsUtil.getAttractions();

        for (VisitedLocation visitedLocation : userLocations) {
            for (Attraction attraction : attractions) {
                if (user.getUserRewards().stream().filter(r -> r.attraction.attractionName.equals(attraction.attractionName)).count() == 0) {
                    if (nearAttraction(visitedLocation, attraction)) {
                        user.addUserReward(new UserReward(visitedLocation, attraction, getRewardPoints(attraction, user)));
                    }
                }
            }
        }
    }

    /**
     * Gets user's rewards updated for each user of a given list
     *
     * @param userList
     */

    public void calculateRewardsMultiThread(List<User> userList) {

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        List<Attraction> attractions = gpsUtil.getAttractions();
        List<Future<?>> listFuture = new ArrayList<>();

        for (User user : userList) {
            Future<?> future = executorService.submit(() -> {

                for (Attraction attraction : attractions) {

                    if (user.getUserRewards().stream().noneMatch(r -> r.attraction.attractionName.equals(attraction.attractionName))) {

                        VisitedLocation lastVisitedLocation = user.getVisitedLocations().get(user.getVisitedLocations().size() - 1);
                        if (nearAttraction(lastVisitedLocation, attraction)) {
                            try {
                                user.addUserReward(new UserReward(lastVisitedLocation, attraction, getRewardPoints(attraction, user)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            listFuture.add(future);
        }

        listFuture.stream().forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {

                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

//====================================================================================
//                                          TOOLS
//====================================================================================
    /**
     * The Tools
     */

    /**
     * Gets access to have the parameter of 'proximity' notion modified
     *
     * @param proximityBuffer
     */
    public void setProximityBuffer(int proximityBuffer) {
        this.proximityBuffer = proximityBuffer;
    }

    /**
     * Gets a boolean to know if an attraction is 'near'
     *
     * @param visitedLocation
     * @param attraction
     * @return reward points
     */
    private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
        return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
    }

    /**
     * Gets rewards points calling rewardCentral
     *
     * @param attraction
     * @param user
     * @return reward points
     */
    public int getRewardPoints(Attraction attraction, User user) {
        return rewardCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId());
    }

    /**
     * Gets distance between 2 locations
     *
     * @param loc1
     * @param loc2
     * @return distance
     */
    public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }


//    private int attractionProximityRange = 200;
//    public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
//        return getDistance(attraction, location) > attractionProximityRange ? false : true;
//    }

//    public void setDefaultProximityBuffer() {
//        proximityBuffer = defaultProximityBuffer;
//    }

//    public List<Attraction> getAttractions() {
//        return gpsUtil.getAttractions();
//    }
//===================================================================================
}

