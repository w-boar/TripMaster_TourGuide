package tourGuide.proxies.tripPricer;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;




@FeignClient(name = "tripPricer", url = "localhost:8083")
public interface TripPricer {

    @RequestMapping(method= RequestMethod.GET, value = "/getPrice")
    List<Provider> getPrice(@RequestParam("apiKey")  String apiKey, @RequestParam("attractionId") UUID attractionId, @RequestParam("adults")  int adults, @RequestParam("children") int children, @RequestParam("nightsStay")  int nightsStay, @RequestParam("rewardsPoints")  int rewardsPoints);

    @RequestMapping(method= RequestMethod.GET, value = "/getProviderName?apiKey={apiKey}&adults={adults}")
    String getProviderName(@RequestParam("apiKey") String apiKey, @RequestParam("adults") int adults);

}
