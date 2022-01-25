package tourGuide.proxies.tripPricer;

import java.util.UUID;

/**
 * The type Provider - proxy
 */
public class Provider {
    public final String name;
    public final double price;
    public final UUID tripId;

    public Provider(UUID tripId, String name, double price) {
        this.name = name;
        this.tripId = tripId;
        this.price = price;
    }

}
