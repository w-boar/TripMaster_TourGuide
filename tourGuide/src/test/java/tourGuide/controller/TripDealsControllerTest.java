package tourGuide.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.service.TripPricerService;
import tourGuide.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TripDealsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService service;

    @Autowired
    TripPricerService tripPricerService;

    @Test
    public void ShouldReturnRewards() throws Exception {
        mockMvc.perform(get("/getRewards")
                        .param("userName", "internalUser1"))
                .andExpect(status().isOk());}

    @Test
    public void ShouldReturnTripDeals() throws Exception {
        mockMvc.perform(get("/getTripDeals")
                        .param("userName", "internalUser1"))
                .andExpect(status().isOk());}

    @Test
    public void ShouldReturnReturnPreferences() throws Exception {
        mockMvc.perform(get("/preferences")
                        .param("userName", "internalUser1")
                .param("numberOfAdults", "1")
        .param("numberOfChildren", "1")
        .param("tripDuration", "1"))
                .andExpect(status().isOk());}


}