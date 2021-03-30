package com.it.example.password.validator.pojo;

public class PasswordValidatorModel {

    private final boolean isValid;

    public PasswordValidatorModel(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

}
