package com.jrandom.dropwizard.example.testingframework.bo;

import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.models.ValidationError;
import com.jrandom.dropwizard.example.testingframework.client.UserAdminResourceClient;
import java.util.List;

public class UserAdminResourceBO {
    private static final String LOGIN="admin";
    private static final String PASSWORD ="admin123";
    private UserAdminResourceClient client = new UserAdminResourceClient(LOGIN, PASSWORD);// this can be done via dependency injection(Guice for ex.) in case of big amount of clients and options
    public List<User> getAllUsers(){
        return client.getAllUsers();
    }
    public User getUser(Long id){
        return client.getUser(id);
    }
    public User createUser(User u){
        return client.createUser(u);
    }
    public User updateUser(User u){
        return client.updateUser(u);
    }
    public boolean deleteUser(Long id){
        return client.deleteUser(id);
    }


    public int statusForGetUserRequest(long id) {
        return client.getUserWithResponceObject(id).getStatusCode();
    }

    public int statusForDeleteUserRequest(long fakeId) {
        return client.deleteUserWithResponceObject(fakeId).getStatusCode();
    }

    public int statusForPutUserRequest(User u) {
        return client.putUserWithResponceObject(u).getStatusCode();
    }

    public ValidationError errorsForCreateUser(User u ) {
        return client.postUserWithResponseObject(u).as(ValidationError.class);
    }
    public ValidationError errorsForUpdateUser(User u ) {
        return client.putUserWithResponceObject(u).as(ValidationError.class);
    }
}