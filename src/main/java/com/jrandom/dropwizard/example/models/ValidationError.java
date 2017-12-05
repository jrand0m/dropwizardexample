package com.jrandom.dropwizard.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationError {
    private String[] errors;

    @JsonProperty
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}
