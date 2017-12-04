package com.jrandom.dropwizard.example.resources;

import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.testingframework.AbstractBaseFunctionalTest;
import static io.restassured.RestAssured.given;
import org.junit.Test;

public class UserAdminResourceNegativeTest extends AbstractBaseFunctionalTest {

    @Test
    public void getFailsGivenAnyInvalidUserId(){

    }
    @Test
    public void deleteRespondsWith404GivenAnyInvalidUserId(){
        User user = new User();
        user.setEmail("ttt@ttt.com");
        user.setUsername("ttt@ttt.com");
        user.setPassword("ttt@ttt.com");
        given().log().body().
                request().
                body(user).
                post("/users/").
                then().
                statusCode(200);


        given().delete("/users/1/").then().statusCode(404);
    }
    @Test
    public void postFailsGivenAnyInvalidUserId(){
        given().delete("/users/nenen/1/").then().statusCode(200);
    }
    @Test
    public void putFailsGivenAnyInvalidUserId(){

    }
}