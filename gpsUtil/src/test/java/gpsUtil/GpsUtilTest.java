package gpsUtil;

import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class GpsUtilTest {

    @Test
    public void shouldReturnAllRegisteredAttraction() {
        GpsUtilService service = new GpsUtilService();
        GpsUtilController controller = new GpsUtilController(service);


        assertEquals(26, controller.getAttractions().size());

    }

    @Test
    public void shouldReturnUserLocation() {
        GpsUtilService service = new GpsUtilService();
        GpsUtilController controller = new GpsUtilController(service);

        VisitedLocation visitedLocation = controller.getUserLocation(UUID.randomUUID());

        assertNotNull(visitedLocation.location);
    }

}
