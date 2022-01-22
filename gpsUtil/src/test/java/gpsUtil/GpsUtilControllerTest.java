package gpsUtil;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class GpsUtilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ShouldReturnUserLocation() throws Exception {
        mockMvc.perform(get("/getUserLocation")
                        .param("userId", "0ea678e4-bebe-4db9-b245-20302aff6ac7"))
                .andExpect(status().isOk());
    }

    @Test
    public void ShouldReturnAttractions() throws Exception {
        mockMvc.perform(get("/getAttractions"))
                .andExpect(status().isOk());
    }
}