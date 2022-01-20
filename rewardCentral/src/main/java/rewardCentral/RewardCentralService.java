package rewardCentral;

import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class RewardCentralService {

    private RewardCentral rewardCentral = new RewardCentral();

    public int getAttractionRewardPoints(UUID attractionId, UUID userId) {

        return rewardCentral.getAttractionRewardPoints(attractionId, userId);
    }

}
