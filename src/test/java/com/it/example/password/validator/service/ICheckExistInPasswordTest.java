package com.it.example.password.validator.service;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ICheckExistInPasswordTest {

    private ICheckExistInPassword checkExistInterface;

    //Scenario from when regex have found a match
    @Test
    void checkExistInPassword_exist() {
        String password = "Aa";

        //The implementation is default, is not necessary to change here.
        checkExistInterface = new ICheckExistInPassword() {
        };

        assertTrue(checkExistInterface.checkExistInPassword("[A-Za-z]", password));

    }

    //Scenario from when regex have not found a match
    @Test
    void checkExistInPassword_notExist() {
        String password = "12345";

        //The implementation is default, is not necessary to change here.
        checkExistInterface = new ICheckExistInPassword() {
        };

        assertFalse(checkExistInterface.checkExistInPassword("[A-Za-z]", password));


    }
}