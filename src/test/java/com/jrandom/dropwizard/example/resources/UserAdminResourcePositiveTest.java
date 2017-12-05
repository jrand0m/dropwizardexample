package com.jrandom.dropwizard.example.resources;

import com.jrandom.dropwizard.example.UserUtils;
import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.testingframework.AdminResourceTest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class UserAdminResourcePositiveTest extends AdminResourceTest {

    @Test
    public void deleteRequestDeletesUserWithGivenId(){
        User u = UserUtils.buildValidUser();
        final long userId = bo.createUser(u).getId();

        boolean statusForGetUserRequest = bo.deleteUser(userId);
        assertThat(statusForGetUserRequest).isTrue();

        bo.getAllUsers().forEach(user -> assertThat(user.getId()).isNotEqualTo(userId));
    }

    @Test
    public void getRequestReturnsUserWithGivenId(){
        User u = UserUtils.buildValidUser();
        u = bo.createUser(u);
        User userReceivedById = bo.getUser(u.getId());
        assertTwoUsersAreSame(u, userReceivedById);

    }

    @Test
    public void putRequestUpdatesUserWithGivenId(){
        final User basicUser = UserUtils.buildValidUser();
        final long userId = bo.createUser(basicUser).getId();
        User updatedUser = UserUtils.buildValidUser();
        updatedUser.setId(userId);
        updatedUser.setFirstName(basicUser.getFirstName()+".mx");
        updatedUser.setLastName(basicUser.getLastName()+".mx");
        updatedUser.setUsername(basicUser.getUsername()+".mx");
        updatedUser.setPassword(basicUser.getPassword()+".mx");
        updatedUser.setEmail(basicUser.getEmail()+".mx");
        User userReceivedFromUpdate = bo.updateUser(updatedUser);
        assertTwoUsersAreSame(updatedUser, userReceivedFromUpdate);
        User userReceivedById = bo.getUser(userId);
        assertTwoUsersAreSame(userReceivedById, userReceivedFromUpdate);
    }

    @Test
    public void postRequestCreatesNewUser(){
        final User basicUser = UserUtils.buildValidUser();
        final User userReturnedByCreateCall = bo.createUser(basicUser);
        basicUser.setId(userReturnedByCreateCall.getId());
        assertTwoUsersAreSame(userReturnedByCreateCall, basicUser);
        User userReceivedById = bo.getUser(userReturnedByCreateCall.getId());
        assertTwoUsersAreSame(userReceivedById, basicUser);
    }

    @Test
    public void getRequestReturnsListOfAllUsersIfNoSpecificIdGiven(){
        List<User> allUsers = bo.getAllUsers();
        assertThat(allUsers).as("initially all users should be empty").hasSize(0);
        final List<User> usersCreated = new ArrayList<>();

        usersCreated.add(bo.createUser(UserUtils.buildValidUser()));
        usersCreated.add(bo.createUser(UserUtils.buildValidUser()));
        usersCreated.add(bo.createUser(UserUtils.buildValidUser()));
        usersCreated.add(bo.createUser(UserUtils.buildValidUser()));
        allUsers = bo.getAllUsers();
        assertThat(allUsers).hasSize(4);
    }

}