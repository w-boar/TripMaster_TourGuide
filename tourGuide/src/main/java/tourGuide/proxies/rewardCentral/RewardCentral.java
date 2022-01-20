package tourGuide.proxies.rewardCentral;//package tourGuide.proxies.rewardCentral;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
@FeignClient(name = "rewardCentral", url = "localhost:8082")
public interface RewardCentral {

    @RequestMapping(method= RequestMethod.GET, value ="/getAttractionRewardPoints?attractionId={attractionId}&userId={userId}")
    public String getAttractionRewardPoints(@RequestParam("attractionId") UUID attractionId, @RequestParam("userId") UUID userId);
}
