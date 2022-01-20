package tourGuide.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.model.User;
import tourGuide.proxies.gpsUtil.VisitedLocation;
import tourGuide.service.LocalisationService;
import tourGuide.service.UserService;

@RestController
public class LocalisationController {

//    @Autowired
//    GpsUtil gpsUtil;
//    @Autowired
//    RewardCentral rewardCentral;
    @Autowired
    UserService userService;
    @Autowired
    LocalisationService localisationService;

//    @GetMapping(value = "/getGpsUtilTested")
//    public String getGpsUtilTested() {
//        return gpsUtil.getGpsUtilTested();
//    }

    @GetMapping(value = "/getGpsUtilTested")
    public String getGpsUtilTested() {
        return localisationService.getGpsUtilTested();
    }

    @GetMapping("/getLocation")
    public String getLocation(@RequestParam String userName) {
        VisitedLocation visitedLocation = localisationService.getUserLocation(userService.getUser(userName));
        return JsonStream.serialize(visitedLocation.getLocation());
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/getAttractions")
//    public List<Attraction> getAttractions() {
//        return gpsUtil.getAttractions();
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/mapAttractions")
//    public TreeMap<Double, Attraction> getAttractionsWithDistance(@RequestParam String userName) {
//        VisitedLocation userLocation = localisationService.getUserLocation(userService.getUser(userName));
//        List<Attraction> attractions = gpsUtil.getAttractions();
//        return localisationService.mapAttractionsWithDistances(userLocation, userService.getUser(userName), attractions);
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/mapAttractions")
//    public TreeMap<Double, Attraction> getAttractionsWithDistance(@RequestParam String userName) {
//
//        return localisationService.mapAttractionsWithDistances(localisationService.getUserLocation(userService.getUser(userName)), userService.getUser(userName));
//    }


//    @RequestMapping(method = RequestMethod.GET, value = "/mapAttractions")
//    public TreeMap<Double, Attraction> getAttractionsWithDistance(@RequestParam String userName) {
//        VisitedLocation userLocation = localisationService.getUserLocation(userService.getUser(userName));
//        List<Attraction> attractions = gpsUtil.getAttractions();
//        return localisationService.mapAttractionsWithDistances(userLocation, userService.getUser(userName), attractions);
//    }
//}

    // Get the closest five tourist attractions to the user - no matter how far away they are.
    //  Return a new JSON object that contains:
    // Name of Tourist attraction,
    // Tourist attractions lat/long,
    // The user's location lat/long,
    // The distance in miles between the user's location and each of the attractions.
    // The reward points for visiting each Attraction.
    // Note: Attraction reward points can be gathered from RewardsCentral

//    @GetMapping("/getNearbyAttractions")
//    public String getNearbyAttractions(@RequestParam String userName) {
//        User user = userService.getUser(userName);
//        VisitedLocation visitedLocation = localisationService.getUserLocation(user);
////        List<Attraction> attractions = gpsUtil.getAttractions();
//        TreeMap<Double, Attraction> attractionsMap = localisationService.mapAttractionsWithDistances(visitedLocation, user);
//        List<String> nearbyAttractions = new ArrayList<>();
//        Set<Double> keys = attractionsMap.keySet();
//        int i = 0;
//        for (double key : keys) {
//            if (i < 5) {
//                Attraction attraction = attractionsMap.get(key);
//                NearbyAttraction nearbyAttraction = new NearbyAttraction(attraction.getLatitude(),
//                        attraction.getLongitude(), attraction, user, visitedLocation, localisationService.getDistance(visitedLocation.getLocation(), attraction),
//                        Integer.parseInt(rewardCentral.getAttractionRewardPoints(attraction.getAttractionId(), user.getUserId())));
//                nearbyAttractions.add(nearbyAttraction.toString());
//                i++;
//            }
//        }
//        return JsonStream.serialize(nearbyAttractions);
//    }

    @GetMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam String userName) {
        User user = userService.getUser(userName);
        VisitedLocation visitedLocation = localisationService.getUserLocation(user);
        return JsonStream.serialize(localisationService.getNearByAttractions(visitedLocation, user));
    }
}
