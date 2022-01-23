package gpsUtil;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type GpsUtil service.
 */
@Service
public class GpsUtilService {
    /**
     * Instantiates a new GpsUtil.
     */
    private GpsUtil gpsUtil = new GpsUtil();

    /**
     * Gets attractions.
     *
     * @return List of attractions
     */
    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

    /**
     * Gets attractions.
     *
     * @param userId
     * @return visited location
     */
    public VisitedLocation getUserLocation(UUID userId) {
        return gpsUtil.getUserLocation(userId);
    }

//    public String getGpsUtilTested() {
//        return "gpsUtilTest";
//    }

}
