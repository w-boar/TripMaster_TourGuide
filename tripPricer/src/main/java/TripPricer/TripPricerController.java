package TripPricer;

import com.jsoniter.output.JsonStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

@RestController
public class TripPricerController {

    private TripPricerService tripPricerService;

    public TripPricerController(TripPricerService tripPricerService) {
        this.tripPricerService = tripPricerService;
    }

    @GetMapping("/getPrice")
    public List<Provider> getPrice(@RequestParam String apiKey, @RequestParam UUID attractionId,
                                   @RequestParam int adults, @RequestParam int children,
                                   @RequestParam int nightsStay, @RequestParam int rewardsPoints) {

        return tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

    @GetMapping("/getProviderName")
    public String getProviderName(@RequestParam String apiKey, @RequestParam int adults) {
        String provider = tripPricerService.getProviderName(apiKey, adults);

        return JsonStream.serialize(provider);
    }

}
