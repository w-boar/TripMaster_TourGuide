package rewardCentral;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * The type RewardCentral controller.
 */
@RestController
public class RewardCentralController {

    private RewardCentralService rewardCentralService;

    /**
     * Instantiates a new GpsUtilController
     *
     * @param rewardCentralService
     */
    public RewardCentralController(RewardCentralService rewardCentralService) {
        this.rewardCentralService = rewardCentralService;
    }

    /**
     * Gets reward points (random method)
     *
     * @param attractionId
     * @param userId
     * @return reward points
     */
    @GetMapping("/getAttractionRewardPoints")
    public int getAttractionRewardPoints(@RequestParam() UUID attractionId, @RequestParam UUID userId) {

        return  rewardCentralService.getAttractionRewardPoints(attractionId, userId);
    }

}
