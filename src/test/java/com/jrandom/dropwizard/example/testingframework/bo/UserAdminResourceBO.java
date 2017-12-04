package com.jrandom.dropwizard.example.testingframework.bo;

import com.jrandom.dropwizard.example.models.User;
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


}