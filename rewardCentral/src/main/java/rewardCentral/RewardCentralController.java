package rewardCentral;

import com.jsoniter.output.JsonStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
public class RewardCentralController {

    private RewardCentralService rewardCentralService;

    public RewardCentralController(RewardCentralService rewardCentralService) {
        this.rewardCentralService = rewardCentralService;
    }

    @GetMapping("/getAttractionRewardPoints")
    public String getAttractionRewardPoints(@RequestParam() UUID attractionId, @RequestParam UUID userId) {

        return  JsonStream.serialize(rewardCentralService.getAttractionRewardPoints(attractionId, userId));
    }

}
