package TripPricer;

import com.jsoniter.output.JsonStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

/**
 * The type TripPricer controller.
 */
@RestController
public class TripPricerController {
    private static final Logger logger = LogManager.getLogger("tripPricer");
    private TripPricerService tripPricerService;

    /**
     * Instantiates a new GpsUtilController
     *
     * @param tripPricerService
     */
    public TripPricerController(TripPricerService tripPricerService) {
        this.tripPricerService = tripPricerService;
    }

    /**
     * Gets reward points (random method)
     *
     * @param apiKey
     * @param attractionId
     * @param adults        number of
     * @param children      number of
     * @param nightsStay    number of
     * @param rewardsPoints number of
     * @return offers
     */
    @GetMapping("/getPrice")
    public List<Provider> getPrice(@RequestParam String apiKey, @RequestParam UUID attractionId,
                                   @RequestParam int adults, @RequestParam int children,
                                   @RequestParam int nightsStay, @RequestParam int rewardsPoints) {
        logger.info("REQUEST: /getPrice?apiKey=" + apiKey + "&attractionId=" + attractionId + "&adults=" + adults + "&children=" + children + "&nightsStay=" + nightsStay + "&rewardsPoints=" + rewardsPoints);
        return tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

    /**
     * Gets reward points (random method)
     *
     * @param apiKey
     * @param adults number of
     * @return provider's name
     */
    @GetMapping("/getProviderName")
    public String getProviderName(@RequestParam String apiKey, @RequestParam int adults) {
        logger.info("REQUEST: /getPrice?apiKey=" + apiKey + "&adults=" + adults);
        String provider = tripPricerService.getProviderName(apiKey, adults);
        return JsonStream.serialize(provider);
    }

}
