package tourGuide.service;

//import gpsUtil.GpsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tourGuide.helper.InternalUsers;
import tourGuide.helper.Tracker;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.webClient.gpsUtil.GpsUtil;
//import tourGuide.proxies.gpsUtil.GpsUtil;
//import tourGuide.proxies.tripPricer.TripPricer;
//import tripPricer.TripPricer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

//    @Autowired
//    TripPricer tripPricer;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

//    private final static String baseUrl = "http://localhost:8081";
//    private WebClient webClient = WebClient.create(baseUrl);
    GpsUtil gpsUtil = new GpsUtil();
    LocalisationService localisationService = new LocalisationService();
//    private final GpsUtil gpsUtil;
//    private final LocalisationService localisationService;
//    private final TripPricer tripPricer = new TripPricer();
    public final Tracker tracker;
    private Map<String, User> internalUserMap = new HashMap<>();
    public InternalUsers internalUsers= new InternalUsers(internalUserMap);
//    public InternalTestHelper internalTestHelper;
    boolean testMode = true;


    public UserService() {
//        this.gpsUtil = gpsUtil;
//        this.localisationService = localisationService;

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            internalUsers.initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
       tracker = new Tracker();
        addShutDownHook();
    }

    public List<UserReward> getUserRewards(User user) {
        return user.getUserRewards();
    }

//    public VisitedLocation getUserLocation(User user) {
//        VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
//                user.getLastVisitedLocation() :
//                trackUserLocation(user);
//        return visitedLocation;
//    }

    public User getUser(String userName) {
        return internalUserMap.get(userName);
    }

    public List<User> getAllUsers() {
        return internalUserMap.values().stream().collect(Collectors.toList());
    }

    public List<String> getAllCurrentLocations() {
        List<String> lastLocations = new ArrayList<String>();
        List<User> users = getAllUsers();
        String lastLocation = "";
        for (User user : users) {
            lastLocation = user.getUserId() + ": " + "{longitude: " + user.getLastVisitedLocation().getLocation().getLongitude() + ", latitude: " + user.getLastVisitedLocation().getLocation().getLatitude() + "}";
            lastLocations.add(lastLocation);
        }
        return lastLocations;
    }

    public void addUser(User user) {
        if (!internalUserMap.containsKey(user.getUserName())) {
            internalUserMap.put(user.getUserName(), user);
        }
    }

//    public List<Provider> getTripDeals(User user) {
//        int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
//        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
//                user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatativeRewardPoints);
//        user.setTripDeals(providers);
//        return providers;
//    }


//    public VisitedLocation trackUserLocation(User user) {
//        VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
//        user.addToVisitedLocations(visitedLocation);
//        rewardsService.calculateRewards(user);
//        return visitedLocation;
//    }
//
//    public void trackUserLocationMultiThread(List<User> userList) {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//
//        List<Future<?>> listFuture = new ArrayList<>();
//
//        for (User user : userList) {
//            Future<?> future = executorService.submit(() -> {
//
//                VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
//                user.addToVisitedLocations(visitedLocation);
//            });
//
//            listFuture.add(future);
//        }
//
//        listFuture.stream().forEach(futureResult -> {
//            try {
//                futureResult.get();
//            } catch (InterruptedException | ExecutionException e) {
//            }
//        });
//
//        executorService.shutdown();
//
//        rewardsService.calculateRewardsMultiThread(userList);
//
//    }

//    public List<String> getNearByAttractions(VisitedLocation visitedLocation, User user) {
//        NearbyAttractionService nearbyAttractionService = new NearbyAttractionService(gpsUtil, rewardsService);
//        return nearbyAttractionService.getNearByAttractions(visitedLocation, user);
//    }

    private void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                tracker.stopTracking();
            }
        });
    }

//    /**********************************************************************************
//     *
//     * Methods Below: For Internal Testing
//     *
//     **********************************************************************************/
//    private static final String tripPricerApiKey = "test-server-api-key";
//    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
//    private final Map<String, User> internalUserMap = new HashMap<>();
//
//    private void initializeInternalUsers() {
//        IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
//            String userName = "internalUser" + i;
//            String phone = "000";
//            String email = userName + "@tourGuide.com";
//            User user = new User(UUID.randomUUID(), userName, phone, email);
//            generateUserLocationHistory(user);
//
//            internalUserMap.put(userName, user);
//        });
//        logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
//    }
//
//    private void generateUserLocationHistory(User user) {
//        IntStream.range(0, 3).forEach(i -> {
//            user.addToVisitedLocations(new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
//        });
//    }
//
//    private double generateRandomLongitude() {
//        double leftLimit = -180;
//        double rightLimit = 180;
//        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
//    }
//
//    private double generateRandomLatitude() {
//        double leftLimit = -85.05112878;
//        double rightLimit = 85.05112878;
//        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
//    }
//
//    private Date getRandomTime() {
//        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
//        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
//    }

}
