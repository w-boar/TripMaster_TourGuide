package tourGuide.service;

import org.springframework.stereotype.Service;
import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.proxies.tripPricer.TripPricer;

import java.util.List;

@Service

public class TripPricerService {
    private static final String tripPricerApiKey = "test-server-api-key";

    private TripPricer tripPricer;

    public TripPricerService(TripPricer tripPricer) {
        this.tripPricer = tripPricer;
    }

public int getCumulativeRewardPoints(User user) {
    return user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
}

    public List<Provider> getTripDeals(User user) {
        int cumulativeRewardPoints = getCumulativeRewardPoints(user);
        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulativeRewardPoints);
        user.setTripDeals(providers);
        return providers;
    }
}
