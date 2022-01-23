package tourGuide.model;

import tourGuide.proxies.gpsUtil.Location;

import java.util.UUID;

public class LastLocation {

    private  UUID userId;
    private Location lastLocation;

    public LastLocation(UUID userId, Location lastLocation) {
        this.userId = userId;
        this.lastLocation = lastLocation;
    }

    public LastLocation() {
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

}
