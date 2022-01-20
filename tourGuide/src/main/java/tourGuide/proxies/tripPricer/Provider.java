package tourGuide.proxies.tripPricer;//package tourGuide.proxies.tripPricer;

import java.util.UUID;

public class Provider {
    public final String name;
    public final double price;
    public final UUID tripId;

    public Provider(UUID tripId, String name, double price) {
        this.name = name;
        this.tripId = tripId;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public UUID getTripId() {
        return tripId;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", tripId=" + tripId +
                '}';
    }
}
