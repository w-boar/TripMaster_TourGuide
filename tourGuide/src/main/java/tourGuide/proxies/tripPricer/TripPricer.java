package tourGuide.proxies.tripPricer;


import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.UUID;


public interface TripPricer {

    @RequestLine("GET /getPrice?apiKey={apiKey}&attractionId={attractionId}&adults={adults}&children={children}&nightsStay={nightsStay}&rewardsPoints={rewardsPoints}")
    List<Provider> getPrice(@Param("apiKey") String apiKey, @Param("attractionId") UUID attractionId, @Param("adults") int adults, @Param("children") int children, @Param("nightsStay") int nightsStay, @Param("rewardsPoints") int rewardsPoints);

    @RequestLine("GET  /getProviderName?apiKey={apiKey}&adults={adults}")
    String getProviderName(@Param("apiKey") String apiKey, @Param("adults") int adults);

}
