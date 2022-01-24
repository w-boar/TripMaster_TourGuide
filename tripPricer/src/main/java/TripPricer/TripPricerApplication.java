package TripPricer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type  TripPricer microservice
 */
@SpringBootApplication
public class TripPricerApplication {
    private static final Logger logger = LogManager.getLogger("tripPricer");
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TripPricerApplication.class, args);
        logger.info("tripPricer initialized");
    }

}
