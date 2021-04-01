package com.it.example.password.validator.service.regex.validator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpperCaseValidatorTest {

    @Test
    void passwordAnalyse() {
        Map<String, Boolean> passwordBank = new HashMap<>();

        passwordBank.put("", false);
        passwordBank.put("ASDFAVXZ", true);
        passwordBank.put("aa", false);
        passwordBank.put("ab", false);
        passwordBank.put("AAAbbbCc", true);
        passwordBank.put("AbTp9!foo", true);
        passwordBank.put("AbTp9!foA", true);
        passwordBank.put("AbTp9 fok", true);
        passwordBank.put("AbTp9!fok", true);

        passwordBank.forEach((password, expectedValidate) -> {
            UpperCaseValidator validator = new UpperCaseValidator(password);

            validator.passwordAnalyse();

            if (validator.isValid() != expectedValidate)
                System.out.println(this.getClass().getSimpleName() + "ERRO => " + " Test Password: [\"" + password + "\"] was not the expected result");

            assertEquals(expectedValidate, validator.isValid());
        });

    }
}