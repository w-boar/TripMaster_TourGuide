package tourGuide.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;
import tourGuide.model.NearbyAttraction;
import tourGuide.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Service
public class NearbyAttractionService {

    private GpsUtil gpsUtil;
    private RewardsService rewardsService;

    public NearbyAttractionService(GpsUtil gpsUtil, RewardsService rewardsService) {
        this.gpsUtil = gpsUtil;
        this.rewardsService = rewardsService;
    }

    public List<String> getNearByAttractions(VisitedLocation visitedLocation, User user) {
        RewardCentral rewardCentral = new RewardCentral();
        List<String> nearbyAttractions = new ArrayList<>();
        TreeMap<Double, Attraction> attractionsMap = new TreeMap<>();
        for (Attraction attraction : gpsUtil.getAttractions()) {
            attractionsMap.put(rewardsService.getDistance(visitedLocation.location, attraction), attraction);

        }
        Set<Double> keys = attractionsMap.keySet();
        int i = 0;
        for (double key : keys) {
            if (i < 5) {
                Attraction attraction = attractionsMap.get(key);
                NearbyAttraction nearbyAttraction = new NearbyAttraction(attraction.latitude, attraction.longitude, attraction, user, visitedLocation, rewardsService, rewardCentral);
                nearbyAttractions.add(nearbyAttraction.toString());
                i++;
            }
        }
        return nearbyAttractions;
    }


}
