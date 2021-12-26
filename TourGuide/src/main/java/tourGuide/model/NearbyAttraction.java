package tourGuide.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourGuide.service.RewardsService;
import tourGuide.user.User;

public class NearbyAttraction extends Location {
    private Attraction attraction;
    private User user;
    private VisitedLocation visitedLocation;
    private RewardsService rewardsService;
    private RewardCentral rewardCentral;

    public NearbyAttraction(double latitude, double longitude, Attraction attraction, User user, VisitedLocation visitedLocation, RewardsService rewardsService, RewardCentral rewardCentral) {
        super(latitude, longitude);
        this.attraction = attraction;
        this.user = user;
        this.visitedLocation= visitedLocation;
        this.rewardsService = rewardsService;
        this.rewardCentral = rewardCentral;
    }

    @Override
    public String toString() {
        return "NearbyAttraction{" +
                "attractionName='" + attraction.attractionName+ '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", user latitude=" + visitedLocation.location.latitude+
                ", user longitude=" + visitedLocation.location.longitude+
                ", distance=" + rewardsService.getDistance(visitedLocation.location, attraction)+
                " miles, reward points =" + rewardCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId()) +
                '}';
    }
}


