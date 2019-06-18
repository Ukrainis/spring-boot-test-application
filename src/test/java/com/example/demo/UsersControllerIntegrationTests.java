package com.example.demo;

import static org.junit.Assert.assertEquals;
import java.net.URI;

import com.example.demo.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersControllerIntegrationTests {

    @LocalServerPort
    private int port;

    private URI url;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        url = new URI("http://localhost:" + port);
    }

    @Test
    public void getUsersTestShouldPass() throws Exception {
        ResponseEntity<String> response = this.template.getForEntity(url.toString() + "/api/users", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void postUserTestShouldFail() throws Exception {
        User user = new User();
        user.setName("Test user");
        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(user);
        ResponseEntity<String> response = this.template.postForEntity(url.toString() + "/api/user", json, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}