package tourGuide.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;
import tourGuide.model.NearbyAttraction;
import tourGuide.model.User;
import tourGuide.model.UserReward;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LocalisationService {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    // proximity in miles
    private int defaultProximityBuffer = 10;
    private int proximityBuffer = defaultProximityBuffer;
    private int attractionProximityRange = 200;
    private final GpsUtil gpsUtil;
    private final RewardCentral rewardsCentral;


    public LocalisationService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
        this.gpsUtil = gpsUtil;
        this.rewardsCentral = rewardCentral;
    }

    public void setProximityBuffer(int proximityBuffer) {
        this.proximityBuffer = proximityBuffer;
    }

    public void setDefaultProximityBuffer() {
        proximityBuffer = defaultProximityBuffer;
    }

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

    public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
        return getDistance(attraction, location) > attractionProximityRange ? false : true;
    }

    private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
        return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
    }

    public int getRewardPoints(Attraction attraction, User user) {
        return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId());
    }

    public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

    public List<String> getNearByAttractions(VisitedLocation visitedLocation, User user) {
        RewardCentral rewardCentral = new RewardCentral();
        List<String> nearbyAttractions = new ArrayList<>();
        TreeMap<Double, Attraction> attractionsMap = new TreeMap<>();
        for (Attraction attraction : gpsUtil.getAttractions()) {
            attractionsMap.put(getDistance(visitedLocation.location, attraction), attraction);

        }
        Set<Double> keys = attractionsMap.keySet();
        int i = 0;
        for (double key : keys) {
            if (i < 5) {
                Attraction attraction = attractionsMap.get(key);
                NearbyAttraction nearbyAttraction = new NearbyAttraction(attraction.latitude,
                        attraction.longitude, attraction, user, visitedLocation, getDistance(visitedLocation.location, attraction),
                        rewardCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId()));
                nearbyAttractions.add(nearbyAttraction.toString());
                i++;
            }
        }
        return nearbyAttractions;
    }

    public VisitedLocation trackUserLocation(User user) {
        VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        calculateRewards(user);
        return visitedLocation;
    }

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
            }
        });

        executorService.shutdown();

        calculateRewardsMultiThread(userList);

    }

    public VisitedLocation getUserLocation(User user) {
        VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
                user.getLastVisitedLocation() :
                trackUserLocation(user);
        return visitedLocation;
    }

}
