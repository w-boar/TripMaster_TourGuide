package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.proxies.gpsUtil.Attraction;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.service.LocalisationService;
import tourGuide.service.UserService;

import java.util.List;
import java.util.TreeMap;

/**
 * The type Localisation controller.
 */
@RestController
public class LocalisationController {


    @Autowired
    private UserService userService;
    @Autowired
    private LocalisationService localisationService;

    /**
     * Gets location
     *
     * @param userName
     * @return location description
     */
    @GetMapping("/getLocation")
    public String getLocation(@RequestParam String userName) {
        VisitedLocation visitedLocation = localisationService.getUserLocation(userService.getUser(userName));
        return JsonStream.serialize(visitedLocation.location);
    }

    //    Get the closest five tourist attractions to the user - no matter how far away they are.
//     Return a new JSON object that contains:
//     Name of Tourist attraction,
//     Tourist attractions lat/long,
//     The user's location lat/long,
//     The distance in miles between the user's location and each of the attractions.
//     The reward points for visiting each Attraction.
//     Note: Attraction reward points can be gathered from RewardsCentral

    /**
     * Gets nearby attractions.
     *
     * @param userName
     * @return the first 5 nearest attractions
     */
    @GetMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam String userName) {
        User user = userService.getUser(userName);
        VisitedLocation visitedLocation = localisationService.getUserLocation(user);
        return JsonStream.serialize(localisationService.getNearByAttractions(user));
    }

//======  Tools ===================================================================================
//    @GetMapping("/getAttractions")
//    public List<Attraction> getAttractions() {
//        return localisationService.getAttractions();
//    }
//
//    @GetMapping("/mapAttractions")
//    public TreeMap<Double, Attraction> getAttractionsWithDistance(@RequestParam String userName) {
//
//        return localisationService.mapAttractionsWithDistances(localisationService.getUserLocation(userService.getUser(userName)), userService.getUser(userName));
//    }
//=================================================================================================


}
