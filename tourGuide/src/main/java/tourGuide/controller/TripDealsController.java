package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.proxies.tripPricer.TripPricer;
import tourGuide.service.TripPricerService;
import tourGuide.service.UserService;

import java.util.List;

@RestController
public class TripDealsController {

    private static final String tripPricerApiKey = "test-server-api-key";
    @Autowired
    TripPricer tripPricer;
    private UserService userService;
    private TripPricerService tripPricerService;

    public TripDealsController(UserService userService, TripPricerService tripPricerService) {
        this.userService = userService;
        this.tripPricerService = tripPricerService;
    }

    @RequestMapping("/getRewards")
    public String getRewards(@RequestParam String userName) {
        return JsonStream.serialize(userService.getUserRewards(userService.getUser(userName)));
    }

    @RequestMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName) {
        return tripPricerService.getTripDeals(userService.getUser(userName));
    }

    @RequestMapping("/preferences")
    public String getUserPreferences(@RequestParam String userName) {
        User user = userService.getUser(userName);

        return user.getUserPreferences().toString();
    }

    @RequestMapping("/preferences/tripDuration")
    public void getTripDurationChanged(@RequestParam String userName, @RequestParam int tripDuration ){
        userService.getUser(userName).getUserPreferences().setTripDuration(tripDuration);
    }

    @RequestMapping("/preferences/numberOfAdults")
    public void getTripNumberOfAdultsChanged(@RequestParam String userName, @RequestParam int numberOfAdults ){
        userService.getUser(userName).getUserPreferences().setNumberOfAdults(numberOfAdults);
    }

    @RequestMapping("/preferences/numberOfChildren")
    public void getTripNumberOfChildrenChanged(@RequestParam String userName, @RequestParam int numberOfChildren ){
        userService.getUser(userName).getUserPreferences().setNumberOfChildren(numberOfChildren);
    }

}
