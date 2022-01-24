package rewardCentral;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type  RewardCentral microservice
 */
@SpringBootApplication
public class RewardCentralApplication {
    private static final Logger logger = LogManager.getLogger("rewardCentral");
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RewardCentralApplication.class, args);
        logger.info("rewardCentral initialized");
    }

}
