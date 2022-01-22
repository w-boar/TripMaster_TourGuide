package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.service.TripPricerService;
import tourGuide.service.UserService;

import java.util.List;

@RestController
public class TripDealsController {

    private static final String tripPricerApiKey = "test-server-api-key";

    @Autowired
    private UserService userService;
    @Autowired
    private TripPricerService tripPricerService;


    @RequestMapping("/getRewards")
    public String getRewards(@RequestParam String userName) {
        return JsonStream.serialize(userService.getUserRewards(userService.getUser(userName)));
    }

    @RequestMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName) {
        return tripPricerService.getTripDeals(userService.getUser(userName));
    }

    @RequestMapping("/preferences")
    public String getUserPreferences(@RequestParam String userName, @RequestParam int numberOfAdults, @RequestParam int numberOfChildren, @RequestParam int tripDuration) {
        User user = userService.getUser(userName);
        userService.getUser(userName).getUserPreferences().setNumberOfAdults(numberOfAdults);
        userService.getUser(userName).getUserPreferences().setNumberOfChildren(numberOfChildren);
        userService.getUser(userName).getUserPreferences().setTripDuration(tripDuration);
        return user.getUserPreferences().toString();
    }

}
