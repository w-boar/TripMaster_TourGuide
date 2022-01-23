package rewardCentral;

import org.springframework.stereotype.Service;


import java.util.UUID;

/**
 * The type RewardCentral service.
 */
@Service
public class RewardCentralService {
    /**
     * Instantiates a new RewardCentral.
     */
    private RewardCentral rewardCentral = new RewardCentral();

    /**
     * Gets attractions.
     *
     * @param attractionId
     * @param userId
     * @return reward points
     */
    public int getAttractionRewardPoints(UUID attractionId, UUID userId) {

        return rewardCentral.getAttractionRewardPoints(attractionId, userId);
    }

}
