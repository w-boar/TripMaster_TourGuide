package TripPricer;

import org.springframework.stereotype.Service;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

/**
 * The type TripPricer service.
 */
@Service
public class TripPricerService {
    /**
     * Instantiates a new TripPricer.
     */
    private TripPricer tripPricer = new TripPricer();

    /**
     * Gets prices.
     *
     * @param apiKey
     * @param attractionId
     * @param adults        number of
     * @param children      number of
     * @param nightsStay    number of
     * @param rewardsPoints number of
     * @return list of providers' offers
     */
    public List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay,
                                   int rewardsPoints) {

        return tripPricer.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

    /**
     * Gets prices.
     *
     * @param apiKey
     * @param adults number of
     * @return provider's name
     */
    public String getProviderName(String apiKey, int adults) {

        return tripPricer.getProviderName(apiKey, adults);
    }

}
