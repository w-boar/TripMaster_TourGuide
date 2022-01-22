package tourGuide.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.service.LocalisationService;
import tourGuide.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LocalisationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService service;

    @Autowired
    LocalisationService localisationService;


    @Test
    public void ShouldReturnUserLocation() throws Exception {
        mockMvc.perform(get("/getLocation")
                        .param("userName", "internalUser1"))
                .andExpect(status().isOk());
    }

    @Test
    public void ShouldReturnNearbyAttractions() throws Exception {
        mockMvc.perform(get("/getNearbyAttractions")
                        .param("userName", "internalUser1"))
                .andExpect(status().isOk());
    }


}