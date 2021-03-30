package com.it.example.password.validator.service.regex.validator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlankSpaceValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void passwordAnalyse() {
        String blankString = "Testing Blank ";
        BlankSpaceValidator blankSpaceValidator = new BlankSpaceValidator(blankString);
        blankSpaceValidator.passwordAnalyse();
        assertEquals(false, blankSpaceValidator.isValid());
    }
}