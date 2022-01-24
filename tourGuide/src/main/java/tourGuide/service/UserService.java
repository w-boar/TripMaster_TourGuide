package tourGuide.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tourGuide.helper.InternalUsers;
import tourGuide.helper.Tracker;
import tourGuide.model.LastLocation;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger("tourGuide");

    private GpsUtil gpsUtil;
    private RewardCentral rewardCentral;
    public final Tracker tracker;
    private Map<String, User> internalUserMap = new HashMap<>();
    public InternalUsers internalUsers = new InternalUsers(internalUserMap);

    /**
     * Instantiates a new UserService.
     *
     * @param gpsUtil
     * @param rewardCentral
     */
    public UserService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
        this.gpsUtil = gpsUtil;
        this.rewardCentral = rewardCentral;

//==============================================================================
//                                           TEST MODE
//==============================================================================
        /**
         * The Test mode.
         */
        boolean testMode = true;

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            internalUsers.initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
        tracker = new Tracker();
        addShutDownHook();
    }

    public void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                tracker.stopTracking();
            }
        });
    }

//==============================================================================

    /**
     * Gets user.
     *
     * @param userName
     * @return user
     */
    public User getUser(String userName) {
        return internalUserMap.get(userName);
    }

    /**
     * Gets all user.
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        return internalUserMap.values().stream().collect(Collectors.toList());
    }

    public void addUser(User user) {
        if (!internalUserMap.containsKey(user.getUserName())) {
            internalUserMap.put(user.getUserName(), user);
        }
    }

    /**
     * Gets user's rewards
     *
     * @param user
     * @return list user's rewards
     */
    public List<UserReward> getUserRewards(User user) {
        return user.getUserRewards();
    }

    /**
     * Gets all user's current location
     *
     * @return list of last positions
     */
    public List<LastLocation> getAllCurrentLocations() {
        List<LastLocation> lastLocations = new ArrayList<LastLocation>();
        List<User> users = getAllUsers();
        LastLocation lastLocation = new LastLocation();
        for (User user : users) {
            lastLocation.setUserId(user.getUserId());
            lastLocation.setLastLocation(user.getLastVisitedLocation().location);
            lastLocations.add(lastLocation);
        }
        return lastLocations;
    }

}
