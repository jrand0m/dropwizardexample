package com.jrandom.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SampleUserListApplication extends Application<SampleUserListConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SampleUserListApplication().run(args);
    }

    @Override
    public String getName() {
        return "SampleUserList";
    }

    @Override
    public void initialize(final Bootstrap<SampleUserListConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SampleUserListConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
