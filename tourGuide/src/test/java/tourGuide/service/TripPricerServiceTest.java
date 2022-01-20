//package tourGuide.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import tourGuide.model.User;
//import tourGuide.proxies.tripPricer.Provider;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//public class TripPricerServiceTest {
//
//    TripPricerService tripPricerService = new TripPricerService();
//
////    @Test
////    public void shouldGetPrice() {
//////        GIVEN
////        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
////        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");
////        User user3 = new User(UUID.randomUUID(), "jon3", "000", "jon3@tourGuide.com");
////        User user4 = new User(UUID.randomUUID(), "jon4", "000", "jon4@tourGuide.com");
////        UserPreferences userPreferencesUser3 = user3.getUserPreferences();
////        userPreferencesUser3.setNumberOfChildren(3);
////        UserPreferences userPreferencesUser4 = user4.getUserPreferences();
////        userPreferencesUser4.setNumberOfChildren(3);
////        userPreferencesUser4.setTripDuration(4);
////        List<Integer> basicPrices = tripPricerService.getProviderBasicPrices();
////        //        WHEN
////        List<Provider> providers = tripPricerService.getPrice("test-server-api-key",  user, basicPrices);
////        List<Provider> providers2 = tripPricerService.getPrice("test-server-api-key",  user2, basicPrices);
////        List<Provider> providers3 = tripPricerService.getPrice("test-server-api-key",  user3, basicPrices);
////        List<Provider> providers4 = tripPricerService.getPrice("test-server-api-key",  user4, basicPrices);
//////        THEN
////        assertEquals(providers.get(0).getPrice(), providers2.get(0).getPrice(), 1);
////        assertNotEquals(providers.get(0).getPrice(), providers3.get(0).getPrice(), 1);
////        assertNotEquals(providers.get(0).getPrice(), providers4.get(0).getPrice(), 1);
////        assertNotEquals(providers3.get(0).getPrice(), providers4.get(0).getPrice(), 1);
////    }
//
//    @Test
//    public void getTripDeals() {
////		GpsUtil gpsUtil = new GpsUtil();
////		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
////		InternalTestHelper.setInternalUserNumber(0);
////        ProviderService providerService = new ProviderService();
//
//        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
////		user.getUserPreferences().setTripDuration(2);
//        List<Provider> providers = tripPricerService.getTripDeals(user);
//
////		providerService.tracker.stopTracking();
//
////		assertNotNull(user.getUserPreferences());
////		assertNotNull(user.getUserPreferences().getTripDuration());
////		assertEquals(2, user.getUserPreferences().getTripDuration());
//        assertEquals(5, providers.size());
//    }
//
//}