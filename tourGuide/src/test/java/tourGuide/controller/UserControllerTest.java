package tourGuide.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService service;

    @Test
    public void ShouldReturnWelcome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLocationList() throws Exception {
        mockMvc.perform(get("/getAllCurrentLocations"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].userId", notNullValue()))
//                .andExpect(jsonPath("$[0].lastLocation", notNullValue()))
        ;
    }

    @Test
    public void ShouldReturnUser() throws Exception {
        mockMvc.perform(get("/getUser"))
                .andExpect(status().isOk());
    }

}