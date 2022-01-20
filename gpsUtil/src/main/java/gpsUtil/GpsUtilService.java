package gpsUtil;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GpsUtilService {

    private GpsUtil gpsUtil = new GpsUtil();

    public String getGpsUtilTested() {
        return "gpsUtilTest";
    }

    public List<Attraction> getAttractions() {
        return gpsUtil.getAttractions();
    }

    public VisitedLocation getUserLocation(UUID userId) {
        return gpsUtil.getUserLocation(userId);
    }
}
