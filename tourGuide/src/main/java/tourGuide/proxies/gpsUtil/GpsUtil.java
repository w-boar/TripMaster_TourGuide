package tourGuide.proxies.gpsUtil;

import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.UUID;

/**
 * The type GpsUtil - proxy
 */
public interface GpsUtil {

    @RequestLine("GET /getUserLocation?userId={userId}")
    public VisitedLocation getUserLocation(@Param("userId") UUID userId);

    @RequestLine("GET /getAttractions")
    public List<Attraction> getAttractions();

}
