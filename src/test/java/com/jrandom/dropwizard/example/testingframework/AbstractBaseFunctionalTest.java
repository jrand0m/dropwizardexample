package com.jrandom.dropwizard.example.testingframework;

import com.jrandom.dropwizard.example.SampleUserListApplication;
import com.jrandom.dropwizard.example.SampleUserListConfiguration;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.filter.log.LogDetail.ALL;
import org.junit.After;
import org.junit.Before;


public abstract class AbstractBaseFunctionalTest {

        public static final DropwizardTestSupport<SampleUserListConfiguration> SUPPORT =
                new DropwizardTestSupport<>(SampleUserListApplication.class, ResourceHelpers.resourceFilePath("loggingEnabledConfig.yml"));

        @Before
        public void beforeClass() {
            SUPPORT.before();
            given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails(ALL)));
        }

        @After
        public  void afterClass() {
            SUPPORT.after();
        }
}
