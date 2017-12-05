package com.jrandom.dropwizard.example.resources;

import com.jrandom.dropwizard.example.UserUtils;
import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.testingframework.AdminResourceTest;
import com.jrandom.dropwizard.example.testingframework.bo.UserAdminResourceBO;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class UserAdminResourceSmokeTest extends AdminResourceTest {

    @Test
    public void smokeTestGetUsers(){
        bo.createUser(UserUtils.buildValidUser());
        bo.createUser(UserUtils.buildValidUser());
        List<User> allUsers = bo.getAllUsers();
        assertThat(allUsers).hasSize(2);
        bo.deleteUser(allUsers.get(0).getId());
        bo.deleteUser(allUsers.get(1).getId());
    }
    @Test
    public void smokeTestCreateUser(){
        User user = bo.createUser(UserUtils.buildValidUser());
        assertThat(user).isNotNull();
        bo.deleteUser(user.getId());
    }

    @Test
    public void smokeTestUpdateUser(){
        User user = bo.createUser(UserUtils.buildValidUser());
        user = bo.updateUser(user);
        assertThat(user).isNotNull();
        bo.deleteUser(user.getId());
    }

    @Test
    public void smokeTestDeleteUser(){
        User user = bo.createUser(UserUtils.buildValidUser());
        boolean userDeleted = bo.deleteUser(user.getId());
        assertThat(userDeleted).isTrue();
    }

}