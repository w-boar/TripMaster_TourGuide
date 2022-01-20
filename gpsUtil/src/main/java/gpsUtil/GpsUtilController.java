package gpsUtil;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsUtilController {

    private GpsUtilService gpsUtilService;

    public GpsUtilController(GpsUtilService gpsUtilService) {
        this.gpsUtilService = gpsUtilService;
    }

    @GetMapping(value = "/getGpsUtilTested")
    public String getGpsUtilTested() {
        return gpsUtilService.getGpsUtilTested();
    }

    @GetMapping(value = "/getUserLocation")
    public VisitedLocation getUserLocation(@RequestParam("userId") UUID userId) {
        return gpsUtilService.getUserLocation(userId);
    }

    @GetMapping(value="/getAttractions")
    public List<Attraction> getAttractions(){
        return gpsUtilService.getAttractions();
    }

}
