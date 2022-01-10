package tourGuide.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public class NearbyAttraction extends Location {
    private Attraction attraction;
    private User user;
    private VisitedLocation visitedLocation;
    private double distance;
    private int rewardPoints;

    public NearbyAttraction(double latitude, double longitude, Attraction attraction, User user, VisitedLocation visitedLocation, double distance, int rewardPoints) {
        super(latitude, longitude);
        this.attraction = attraction;
        this.user = user;
        this.visitedLocation = visitedLocation;
        this.distance = distance;
        this.rewardPoints = rewardPoints;
    }


    @Override
    public String toString() {
        return "NearbyAttraction{" +
                "attractionName='" + attraction.attractionName+ '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", user latitude=" + visitedLocation.location.latitude+
                ", user longitude=" + visitedLocation.location.longitude+
                ", distance=" + distance+
                " miles, reward points =" + rewardPoints +
                '}';
    }
}


