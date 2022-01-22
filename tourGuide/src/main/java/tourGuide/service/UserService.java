package tourGuide.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourGuide.helper.InternalUsers;
import tourGuide.helper.Tracker;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.rewardCentral.RewardCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private GpsUtil gpsUtil;
    private RewardCentral rewardCentral;


    LocalisationService localisationService = new LocalisationService(gpsUtil, rewardCentral);

    public final Tracker tracker;
    private Map<String, User> internalUserMap = new HashMap<>();
    public InternalUsers internalUsers = new InternalUsers(internalUserMap);
    ;
    boolean testMode = true;


    public UserService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
        this.gpsUtil = gpsUtil;
        this.rewardCentral = rewardCentral;

//==============================================================================
//                                           TEST MODE
//==============================================================================

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

    public User getUser(String userName) {
        return internalUserMap.get(userName);
    }

    public List<User> getAllUsers() {
        return internalUserMap.values().stream().collect(Collectors.toList());
    }

    public void addUser(User user) {
        if (!internalUserMap.containsKey(user.getUserName())) {
            internalUserMap.put(user.getUserName(), user);
        }
    }

    public List<UserReward> getUserRewards(User user) {
        return user.getUserRewards();
    }

    public List<String> getAllCurrentLocations() {
        List<String> lastLocations = new ArrayList<String>();
        List<User> users = getAllUsers();
        String lastLocation = "";
        for (User user : users) {
            lastLocation = user.getUserId() + ": " + "{longitude: " + user.getLastVisitedLocation().location.getLongitude() + ", latitude: " + user.getLastVisitedLocation().location.getLatitude() + "}";
            lastLocations.add(lastLocation);
        }
        return lastLocations;
    }

}
