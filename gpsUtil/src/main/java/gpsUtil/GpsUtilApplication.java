package gpsUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type GpsUtil microservice
 */
@SpringBootApplication
public class GpsUtilApplication {
    private static final Logger logger = LogManager.getLogger("gpsUtil");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GpsUtilApplication.class, args);
        logger.info("gpsUtil initialized");
    }

}
