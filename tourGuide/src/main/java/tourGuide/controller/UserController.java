package tourGuide.controller;

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

    @Autowired
    private UserService userService;

    /**
     * Index string.
     *
     * @return "Greetings from TourGuide!"
     */
    @RequestMapping("/")
    public String index() {
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
     * @return
     */
    @RequestMapping("/getAllCurrentLocations")
    public List<LastLocation> getAllCurrentLocations() {
        return userService.getAllCurrentLocations();
    }

}