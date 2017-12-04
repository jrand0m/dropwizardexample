package com.jrandom.dropwizard.example.testingframework.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrandom.dropwizard.example.models.User;
import io.dropwizard.jackson.Jackson;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;

public class UserAdminResourceClient {

    public static final String USERS = "/users/";
    public static final String USERS_ID = "/users/{id}/";

    public UserAdminResourceClient(String login, String password) {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(login);
        authScheme.setPassword(password);
        RestAssured.authentication = authScheme;
    }

    public boolean deleteUser(Long id) {
        return given().delete(USERS_ID, id).andReturn().getStatusCode() == HttpStatus.SC_NO_CONTENT;
    }

    public User updateUser(User u) {
        User body = given().contentType(ContentType.JSON).body(u).put(USERS_ID, u.getId()).andReturn().as(User.class);
        return body;
    }

    public User createUser(User u) {
        User body = given().contentType(ContentType.JSON).body(u).post(USERS).andReturn().as(User.class);
        return body;
    }

    public User getUser(Long id) {
        User body = given().queryParam("id", id).get(USERS).andReturn().as(User.class);
        return body;
    }
    public List<User> getAllUsers(){
        return Arrays.asList(given().get(USERS).andReturn().as(User[].class));
    }
}
