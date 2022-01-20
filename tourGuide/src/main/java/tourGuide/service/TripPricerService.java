package tourGuide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;

import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.proxies.tripPricer.TripPricer;


import java.util.List;

@Service
@EnableDiscoveryClient
public class TripPricerService {
    private static final String tripPricerApiKey = "test-server-api-key";

    @Autowired
    TripPricer tripPricer;

    public List<Provider> getTripDeals(User user) {
//        TripPricer tripPricer = new TripPricer();
        int cumulativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulativeRewardPoints);
        user.setTripDeals(providers);
        return providers;
    }


//    public List<Provider> getPrice(String apiKey, User user, List<Integer> basicPrices) {
//
//        UUID attractionId = user.getUserId();
//        int adults = user.getUserPreferences().getNumberOfAdults();
//        int children = user.getUserPreferences().getNumberOfChildren();
//        int nightsStay = user.getUserPreferences().getTripDuration();
//        int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
//
//        List<Provider> providers = new ArrayList();
//        HashSet providersUsed = new HashSet();
//
//        try {
//            TimeUnit.MILLISECONDS.sleep((long) ThreadLocalRandom.current().nextInt(1, 50));
//        } catch (InterruptedException var16) {
//        }
//
//        for (int basicPrice : basicPrices) {
//            double childrenDiscount = (double) (children / 3);
//            double price = ((double) (basicPrice * adults) + (double) basicPrice * childrenDiscount) * (double) nightsStay + 0.99D - (double) cumulatativeRewardPoints;
//            if (price < 0.0D) {
//                price = 0.0D;
//            }
//
//            String provider = "";
//
//            do {
//                provider = this.getProviderName(apiKey, adults);
//            } while (providersUsed.contains(provider));
//
//            providersUsed.add(provider);
//            providers.add(new Provider(attractionId, provider, price));
//        }
//
//        return providers;
//    }
//
//    public List<Integer> getProviderBasicPrices() {
//        List<Integer> basicPrices = new ArrayList<>();
//        for (int i = 0; i < 5; ++i) {
//            int basicPrice = ThreadLocalRandom.current().nextInt(100, 700);
//            basicPrices.add(basicPrice);
//        }
//        return basicPrices;
//    }
//
//    public String getProviderName(String apiKey, int adults) {
//        int multiple = ThreadLocalRandom.current().nextInt(1, 10);
//        switch (multiple) {
//            case 1:
//                return "Holiday Travels";
//            case 2:
//                return "Enterprize Ventures Limited";
//            case 3:
//                return "Sunny Days";
//            case 4:
//                return "FlyAway Trips";
//            case 5:
//                return "United Partners Vacations";
//            case 6:
//                return "Dream Trips";
//            case 7:
//                return "Live Free";
//            case 8:
//                return "Dancing Waves Cruselines and Partners";
//            case 9:
//                return "AdventureCo";
//            default:
//                return "Cure-Your-Blues";
//        }
//    }
//
//    public List<Provider> getTripDeals(User user) {
//        List<Provider> providers = getPrice(tripPricerApiKey, user, getProviderBasicPrices());
//        user.setTripDeals(providers);
//        return providers;
//    }


}
