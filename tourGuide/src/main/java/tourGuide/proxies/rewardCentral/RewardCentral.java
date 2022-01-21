package tourGuide.proxies.rewardCentral;

import feign.Param;
import feign.RequestLine;

import java.util.UUID;

public interface RewardCentral {

    @RequestLine("GET /getAttractionRewardPoints?attractionId={attractionId}&userId={userId}")
    int getAttractionRewardPoints(@Param("attractionId") UUID attractionId, @Param("userId") UUID userId);
}
