package tourGuide.proxies.gpsUtil;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Service
@FeignClient(name = "gpsUtil",  url = "localhost:8081")
public interface GpsUtil {

    @RequestMapping(method= RequestMethod.GET, value = "/getGpsUtilTested")
    String getGpsUtilTested();

    @RequestMapping(method= RequestMethod.GET, value = "/getUserLocation?userId={userId}")
    public VisitedLocation getUserLocation(@RequestParam("userId") UUID userId);

    @RequestMapping(method= RequestMethod.GET, value="/getAttractions")
    public List<Attraction> getAttractions();

}
