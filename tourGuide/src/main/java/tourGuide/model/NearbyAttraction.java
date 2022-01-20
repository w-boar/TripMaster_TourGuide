package tourGuide.model;


import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.Location;
import tourGuide.proxies.gpsUtil.VisitedLocation;

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
                "attractionName='" + attraction.getAttractionName()+ '\'' +
                ", latitude=" + attraction.getLatitude() +
                ", longitude=" + attraction.getLongitude() +
                ", user latitude=" + visitedLocation.getLocation().getLatitude()+
                ", user longitude=" + visitedLocation.getLocation().getLongitude()+
                ", distance=" + distance+
                " miles, reward points =" + rewardPoints +
                '}';
    }
}


