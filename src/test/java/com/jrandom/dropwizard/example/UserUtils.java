package com.jrandom.dropwizard.example;

import com.jrandom.dropwizard.example.models.User;

public class UserUtils {
    public static User buildValidUser() {
        User u =  new User();
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setUsername("john.doe");
        u.setPassword("MyFriendJohnIsTheSeriousOne");
        u.setEmail("John.Doe@exists.not.com");
        return u;
    }
}
