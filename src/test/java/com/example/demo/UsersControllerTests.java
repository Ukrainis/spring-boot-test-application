package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {

    @Autowired
    private MockMvc mock;

    @Test
    public void getAllUsersTest() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void postUserShouldFailTest() throws Exception {
        User user = new User();
        user.setName("Test user");
        mock.perform(MockMvcRequestBuilders.post("/api/user", user).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

}