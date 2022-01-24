package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.proxies.tripPricer.Provider;
import tourGuide.service.TripPricerService;
import tourGuide.service.UserService;

import java.util.List;

/**
 * The type TripDeals controller.
 */
@RestController
public class TripDealsController {
    private static final Logger logger = LogManager.getLogger("tourGuide");
    private static final String tripPricerApiKey = "test-server-api-key";

    @Autowired
    private UserService userService;
    @Autowired
    private TripPricerService tripPricerService;


    @RequestMapping("/getRewards")
    public String getRewards(@RequestParam String userName) {
        logger.info("REQUEST: /getRewards?userName=" + userName);
        return JsonStream.serialize(userService.getUserRewards(userService.getUser(userName)));
    }


    /**
     * Gets trip deals.
     *
     * @param userName
     * @return a list of offers
     */
    @RequestMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName)  {
        logger.info("REQUEST: /getTripDeals?userName=" + userName);
        return tripPricerService.getTripDeals(userService.getUser(userName));
    }

    /**
     * Gets access to have user's preferences updated
     *
     * @param userName
     * @param numberOfAdults
     * @param numberOfChildren
     * @param tripDuration
     * @return updated preferences
     */
    @RequestMapping("/preferences")
    public String getUserPreferences(@RequestParam String userName, @RequestParam int numberOfAdults, @RequestParam int numberOfChildren, @RequestParam int tripDuration)  {
        logger.info("REQUEST: /preferences?userName=" + userName + "&numberOfAdults=" + numberOfAdults + "&numberOfChildren=" + numberOfChildren + "&tripDuration=" + tripDuration);
        User user = userService.getUser(userName);
        userService.getUser(userName).getUserPreferences().setNumberOfAdults(numberOfAdults);
        userService.getUser(userName).getUserPreferences().setNumberOfChildren(numberOfChildren);
        userService.getUser(userName).getUserPreferences().setTripDuration(tripDuration);
        return user.getUserPreferences().toString();
    }

}
