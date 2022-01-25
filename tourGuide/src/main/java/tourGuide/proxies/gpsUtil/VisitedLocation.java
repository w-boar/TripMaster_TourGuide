package tourGuide.proxies.gpsUtil;

import java.util.Date;
import java.util.UUID;

/**
 * The type VisitedLocation - proxy
 */
public class VisitedLocation {
    public final UUID userId;
    public final Location location;
    public final Date timeVisited;

    public VisitedLocation(UUID userId, Location location, Date timeVisited) {
        this.userId = userId;
        this.location = location;
        this.timeVisited = timeVisited;
    }

}
