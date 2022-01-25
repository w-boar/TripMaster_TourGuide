package tourGuide;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tourGuide.proxies.gpsUtil.GpsUtil;
import tourGuide.proxies.rewardCentral.RewardCentral;
import tourGuide.proxies.tripPricer.TripPricer;
import tourGuide.service.LocalisationService;

/**
 * The type TourGuideModule - Feign Configuration
 */
@Configuration
    public class TourGuideModule {

        @Value("${gpsutil.port}")
        private String gpsUtilPort;

        @Value("${rewardcentral.port}")
        private String rewardCentralPort;

        @Value("${trippricer.port}")
        private String tripPricerPort;

    /**
     * Builds gpsUtil
     *
     */
        @Bean
        public GpsUtil getGpsUtil() {
            return Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class,  gpsUtilPort);
        }

    /**
     * helps to instantiate localisationService
     *
     */
    @Bean
        public LocalisationService localisationService() { return new LocalisationService(getGpsUtil(), getRewardCentral());
        }

    /**
     * Builds rewardCentral
     *
     */
        @Bean
        public RewardCentral getRewardCentral() {
            return Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class,  rewardCentralPort);
        }

    /**
     * Builds tripPricer
     *
     */
        @Bean
        public TripPricer getTripPricer() {
            return Feign.builder()
                    .decoder(new GsonDecoder())
                    .target(tourGuide.proxies.tripPricer.TripPricer.class,  tripPricerPort);

        }

    }


