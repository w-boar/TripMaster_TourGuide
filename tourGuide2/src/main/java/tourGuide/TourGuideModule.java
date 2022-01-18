package tourGuide;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import tourGuide.webClient.gpsUtil.GpsUtil;

//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
////import gpsUtil.GpsUtil;
////import rewardCentral.RewardCentral;
//import tourGuide.proxies.gpsUtil.GpsUtil;
//import tourGuide.service.LocalisationService;
//
//@Configuration
//public class TourGuideModule {
//
//    private final static String baseUrl = "http://localhost:8081";
//    private WebClient webClient = WebClient.create(baseUrl);
//    @Bean
//    public GpsUtil gpsUtil(){
//        return new GpsUtil(webClient);
//    }
//}
////
////	@Bean
////	public GpsUtil getGpsUtil() {
////		return new GpsUtil();
////	}
//
////	@Autowired
////	private GpsUtil gpsUtil;
////
////    @Autowired
////    private RewardCentral rewardCentral;
//
////	@Bean
////	public LocalisationService getRewardsService() {
////		return new LocalisationService(gpsUtil, getRewardCentral());
////	}
//
////	@Bean
////	public RewardCentral getRewardCentral() {
////		return new RewardCentral();
////	}
//
////    @Bean
////    public LocalisationService getRewardsService() {
////        return new LocalisationService();
////    }
//
//
//}
