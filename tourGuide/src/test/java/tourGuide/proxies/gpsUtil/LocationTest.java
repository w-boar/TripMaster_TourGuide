package tourGuide.proxies.gpsUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationTest {
    @Test
    public void shouldReturnToString() {
//       GIVEN
        Location loc = new Location(26.890959D, -80.116577D);
//        THEN
        assertEquals(("Location{" +
                "longitude=" + -80.116577D +
                ", latitude=" + 26.890959D +
                '}'), loc.toString());
    }
}