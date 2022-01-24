package tourGuide.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.LastLocation;
import tourGuide.model.User;
import tourGuide.service.UserService;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
public class UserController {
    private static final Logger logger = LogManager.getLogger("tourGuide");

    @Autowired
    private UserService userService;

    /**
     * Index string.
     *
     * @return "Greetings from TourGuide!"
     */
    @RequestMapping("/")
    public String index() {
        logger.info("REQUEST: /");
        return "Greetings from TourGuide!";
    }

    /**
     * Gets user
     *
     * @param userName
     * @return user
     */
    @RequestMapping("/getUser")
    public User getUser(String userName) {
        logger.info("REQUEST: /getUser?userName=" + userName);
        return userService.getUser(userName);
    }

//     Get a list of every user's most recent location as JSON
//    - Note:
//    does not use gpsUtil to query for their current location,
//    but rather gathers the user's current location from their stored location history.
//    -Return object should be the just a JSON mapping of userId to Locations similar to:
//         {
//            "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371}
//            ...
//         }

    /**
     * Gets a list of current location for all users
     *
     * @return a list of registered  positions
     */
    @RequestMapping("/getAllCurrentLocations")
    public List<LastLocation> getAllCurrentLocations() {
        logger.info("REQUEST: /getAllCurrentLocations");
        return userService.getAllCurrentLocations();
    }

}