package com.jrandom.dropwizard.example.resources;

import com.jrandom.dropwizard.example.UserUtils;
import com.jrandom.dropwizard.example.models.User;
import com.jrandom.dropwizard.example.models.ValidationError;
import com.jrandom.dropwizard.example.testingframework.AdminResourceTest;
import org.apache.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class UserAdminResourceNegativeTest extends AdminResourceTest {
    private static final long FAKE_ID = 999;
    private static final String VALIDATION_ERROR_MALFORMED_EMAIL = "email not a well-formed email address";
    public static final String VALIDATION_PASSWORD_MAY_NOT_BE_EMPTY = "password may not be empty";
    public static final String VALIDATION_USERNAME_MAY_NOT_BE_EMPTY = "username may not be empty";

    @Test
    public void getFailsGivenAnyInvalidUserId(){
        int statusForGetUserRequest = bo.statusForGetUserRequest(FAKE_ID);
        assertThat(statusForGetUserRequest).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
    @Test
    public void deleteRespondsWith404GivenAnyInvalidUserId(){
        int statusForGetUserRequest = bo.statusForDeleteUserRequest(FAKE_ID);
        assertThat(statusForGetUserRequest).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void putFailsGivenAnyInvalidUserId(){
        User user = UserUtils.buildValidUser();
        user.setId(FAKE_ID);
        int status = bo.statusForPutUserRequest(user);
        assertThat(status).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void postFailsGivenAnyMalformedEmail(){
        User user = UserUtils.buildMalformedEmailUser();
        ValidationError validationError = bo.errorsForCreateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_ERROR_MALFORMED_EMAIL);
    }
    @Test
    public void putFailsGivenAnyMalformedEmail(){
        User user = bo.createUser(UserUtils.buildValidUser());
        user.setEmail("malformed!");
        ValidationError validationError = bo.errorsForUpdateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_ERROR_MALFORMED_EMAIL);
    }

    @Test
    public void postFailsGivenAnyRequiredFieldMissing(){
        User user = UserUtils.buildMissingPasswordUser();
        ValidationError validationError = bo.errorsForCreateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_PASSWORD_MAY_NOT_BE_EMPTY);
        user = UserUtils.buildMissingUserNameUser();
        validationError = bo.errorsForCreateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_USERNAME_MAY_NOT_BE_EMPTY);

    }
    @Test
    public void putFailsGivenAnyRequiredFieldMissing(){
        User user = bo.createUser(UserUtils.buildValidUser());
        user.setUsername(null);
        ValidationError validationError = bo.errorsForUpdateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_USERNAME_MAY_NOT_BE_EMPTY);
        user = bo.createUser(UserUtils.buildValidUser());
        user.setPassword(null);
        validationError = bo.errorsForUpdateUser(user);
        assertThat(validationError.getErrors().length).isEqualTo(1);
        assertThat(validationError.getErrors()[0]).isEqualTo(VALIDATION_PASSWORD_MAY_NOT_BE_EMPTY);
    }
}