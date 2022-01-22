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


@Configuration
    public class TourGuideModule {

        @Value("${gpsutil.port}")
        private String gpsUtilPort;

        @Value("${rewardcentral.port}")
        private String rewardCentralPort;

        @Value("${trippricer.port}")
        private String tripPricerPort;

        @Bean
        public GpsUtil getGpsUtil() {
            return Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.gpsUtil.GpsUtil.class,  gpsUtilPort);
        }

        @Bean
        public LocalisationService getRewardsService() { return new LocalisationService(getGpsUtil(), getRewardCentral());
        }

        @Bean
        public RewardCentral getRewardCentral() {
            return Feign.builder().decoder(new GsonDecoder()).target(tourGuide.proxies.rewardCentral.RewardCentral.class,  rewardCentralPort);
        }

        @Bean
        public TripPricer getTripPricer() {
            return Feign.builder()
                    .decoder(new GsonDecoder())
                    .target(tourGuide.proxies.tripPricer.TripPricer.class,  tripPricerPort);

        }

    }


