package tourGuide;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type  TourGuide microservice
 */
@SpringBootApplication
public class TourGuideApplication {
    private static final Logger logger = LogManager.getLogger("tourGuide");
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TourGuideApplication.class, args);
        logger.info("tourGuide initialized");
    }

}
