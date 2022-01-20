package rewardCentral;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class RewardCentralTest {

    @Test
    public void shouldReturnAttractionRewardPoint() {
        RewardCentralService rewardCentralService = new RewardCentralService();
        RewardCentralController rewardCentralController = new RewardCentralController(rewardCentralService);

        assertNotNull(rewardCentralController.getAttractionRewardPoints(UUID.randomUUID(),UUID.randomUUID()));
    }

}
