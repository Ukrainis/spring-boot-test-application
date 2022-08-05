package com.example.demo;


import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.matching.MultipartValuePatternBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class UserControllerIT {

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension
            .newInstance().options(wireMockConfig().dynamicPort().extensions(new ResponseTemplateTransformer(true))).build();

    @Test
    void getUsersAcceptJsonTest() {
        String resultJson = "[{\"id\":1227,\"name\":\"Edward\",\"userName\":\"eddelric2\",\"email\":\"edward.elric@alchemy.ha\",\"address\":null,\"phone\":\"12345678\",\"website\":\"alchemy.ha\",\"company\":null}]";
        wireMockServer.stubFor(
                WireMock.get("/api/users")
                        .willReturn(
                                aResponse().withHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(resultJson)
                                        .withStatus(200)
                                        .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)));

        given()
                .accept(ContentType.JSON).log().all()
                .when()
                .get(wireMockServer.baseUrl() + "/api/users")
                .then().log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("userName[0]", equalTo("eddelric2"))
                .body("name[0]", equalTo("Edward"));
    }

    @Test
    public void getUsersAcceptXmlTest() {
        String xmlResult = "<List><item><id>1227</id><name>Edward</name><userName>eddelric2</userName><email>edward.elric@alchemy.ha</email><address/><phone>12345678</phone><website>alchemy.ha</website><company/></item></List>";
        wireMockServer.stubFor(
                WireMock.get("/api/users")
                        .willReturn(
                                aResponse().withHeader("Accept", MediaType.APPLICATION_XML_VALUE).withBody(xmlResult).withStatus(200).withHeader("Content-type", MediaType.APPLICATION_XML_VALUE)));

        given().accept(ContentType.XML).log().all().when().get(wireMockServer.baseUrl() + "/api/users").then().log().all().contentType(ContentType.XML).statusCode(200);
    }

    @Test
    public void createUserJsonTest() {
        wireMockServer.stubFor(
                WireMock.post("/api/createUserPost")
                        .withRequestBody(matchingJsonPath("name", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("userName", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("email", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("website", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("phone", matching("^(?!\\s*$).+")))
                        .willReturn(
                                aResponse()
                                        .withHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody("id")
                                        .withStatus(201)));

        Map<String, String> map = new HashMap<>();
        map.put("userName", "test");
        map.put("name", "test");
        map.put("email", "test");
        map.put("website", "test");
        map.put("phone", "test");
        given()
                .accept(ContentType.JSON).log().all().body(map)
                .when()
                .post(wireMockServer.baseUrl() + "/api/createUserPost")
                .then().log().all()
                .contentType(ContentType.JSON).statusCode(201);
    }

    @Test
    public void createUserFormTest() {
        wireMockServer.stubFor(
                WireMock.post("/api/createUserPost")
                        .withRequestBody(matchingJsonPath("name", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("userName", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("email", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("website", matching("^(?!\\s*$).+")))
                        .withRequestBody(matchingJsonPath("phone", matching("^(?!\\s*$).+")))
                        .willReturn(
                                aResponse()
                                        .withHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody("{\"id\": 1, \n\"userName\": \"{{jsonPath request.body 'userName'}}\"}")
                                        .withStatus(201)));

        Map<String, String> map = new HashMap<>();
        map.put("userName", "Valentins");
        map.put("name", "");
        map.put("email", "test");
        map.put("website", "test");
        map.put("phone", "test");
        given()
                .accept(ContentType.JSON).log().all().body(map)
                .when()
                .post(wireMockServer.baseUrl() + "/api/createUserPost")
                .then().log().all()
                .contentType(ContentType.JSON).statusCode(201)
                .body("userName", equalTo(map.get("userName")));
    }

    @Test
    public void createUserJsonTestNegative() {
        wireMockServer.stubFor(
                WireMock.post("/api/createUserPost")
                        .withRequestBody(
                                matchingJsonPath("userName", WireMock.equalTo("InvalidUserDataException")))
                        .willReturn(
                                aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody("{\n \"exception\": \"InvalidUserDataException\",\n \"message\": \"Incorrect user. Please validate if all mandatory data is filled.\"\n}")
                                        .withStatus(400)
                                        .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)));

        Map<String, String> map = new HashMap<>();
        map.put("userName", "InvalidUserDataException");
        map.put("name", "test");
        map.put("email", "test");
        map.put("website", "test");
        map.put("phone", "test");
        given()
                .accept(ContentType.JSON).log().all().body(map)
                .when()
                .post(wireMockServer.baseUrl() + "/api/createUserPost")
                .then().log().all()
                .contentType(ContentType.JSON).statusCode(400)
                .and()
                .body("exception", equalTo("InvalidUserDataException"))
                .body("message", equalTo("Incorrect user. Please validate if all mandatory data is filled."));
    }
}
