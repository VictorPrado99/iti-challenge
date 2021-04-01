package com.it.example.password.validator.service.regex.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    private Validator validator;

    //Scenario from when regex have found a match
    @Test
    void checkExistInPassword_exist() {
        String password = "Aa";

        validator = new Validator(password) {
            @Override
            protected void passwordAnalyse() {
                isValid = checkExistInPassword("[A-Za-z]");
            }
        };

        validator.passwordAnalyse();

        assertTrue(validator.isValid());

    }

    //Scenario from when regex have not found a match
    @Test
    void checkExistInPassword_notExist() {
        String password = "12345";

        validator = new Validator(password) {
            @Override
            protected void passwordAnalyse() {
                isValid = true; //Make sure you return the wrong statement if existInPassoword didn't do their job
                isValid = checkExistInPassword("[A-Za-z]");
            }
        };

        validator.passwordAnalyse();

        assertFalse(validator.isValid());

    }
}