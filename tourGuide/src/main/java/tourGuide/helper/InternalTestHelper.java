package tourGuide.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.service.UserService;

public class InternalTestHelper {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    // Set this default up to 100,000 for testing
    private static int internalUserNumber = 100;

    public static void setInternalUserNumber(int internalUserNumber) {
        InternalTestHelper.internalUserNumber = internalUserNumber;
    }

    public static int getInternalUserNumber() {
        return internalUserNumber;
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
