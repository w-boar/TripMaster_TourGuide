package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
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
     
    @RequestMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
        return JsonStream.serialize(userService.getAllCurrentLocations());
    }

    @RequestMapping("/getUser")
    public User getUser(String userName) {
        return userService.getUser(userName);
    }


}