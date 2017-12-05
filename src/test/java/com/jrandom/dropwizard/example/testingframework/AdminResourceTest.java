package com.jrandom.dropwizard.example.testingframework;

import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.testingframework.bo.UserAdminResourceBO;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;

public abstract class AdminResourceTest extends AbstractBaseFunctionalTest {
    protected UserAdminResourceBO bo = new UserAdminResourceBO();

    @After
    public void cleanUpDatabase(){
        List<User> allUsers = bo.getAllUsers();
        for (User allUser : allUsers) {
            bo.deleteUser(allUser.getId());
        }
    };

    protected void assertTwoUsersAreSame(User u, User userRecievedById) {
        assertThat(userRecievedById).isNotNull();
        assertThat(userRecievedById.getId()).as("id field").isEqualTo(u.getId());
        assertThat(userRecievedById.getEmail()).as("email field").isEqualTo(u.getEmail());
        assertThat(userRecievedById.getFirstName()).as("First Name field").isEqualTo(u.getFirstName());
        assertThat(userRecievedById.getLastName()).as("LastName field").isEqualTo(u.getLastName());
        assertThat(userRecievedById.getUsername()).as("username field").isEqualTo(u.getUsername());
        assertThat(userRecievedById.getPassword()).as("password field").isEqualTo(u.getPassword());
    }

}
