package gpsUtil;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * The type GpsUtil controller.
 */
@RestController
public class GpsUtilController {
    private static final Logger logger = LogManager.getLogger("gpsUtil");
    private GpsUtilService gpsUtilService;

    /**
     * Instantiates a new GpsUtilController
     *
     * @param gpsUtilService
     */
    public GpsUtilController(GpsUtilService gpsUtilService) {
        this.gpsUtilService = gpsUtilService;
    }

    /**
     * Gets location.
     *
     * @param userId
     * @return visited location
     */
    @GetMapping(value = "/getUserLocation")
    public VisitedLocation getUserLocation(@RequestParam("userId") UUID userId) {
        logger.info("REQUEST: /getUserLocation?userId=" + userId);
        return gpsUtilService.getUserLocation(userId);
    }

    /**
     * Gets attractions.
     *
     * @return a list of all the attractions
     */
    @GetMapping(value = "/getAttractions")
    public List<Attraction> getAttractions() {
        logger.info("REQUEST: /getAttractions");
        return gpsUtilService.getAttractions();
    }

//    @GetMapping(value = "/getGpsUtilTested")
//    public String getGpsUtilTested() {
//        return gpsUtilService.getGpsUtilTested();
//    }


}
