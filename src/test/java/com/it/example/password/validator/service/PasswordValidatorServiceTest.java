package com.it.example.password.validator.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordValidatorServiceTest {

    @Test
    void isValid() {
        Map<String, Boolean> passwordBank = new HashMap<>();

        passwordBank.put("", false);
        passwordBank.put("aa", false);
        passwordBank.put("ab", false);
        passwordBank.put("AAAbbbCc", false);
        passwordBank.put("AbTp9!foo", false);
        passwordBank.put("AbTp9!foA", false);
        passwordBank.put("AbTp9 fok", false);
        passwordBank.put("AbTp9!fok", true);

        passwordBank.forEach((password, expectedValidate) -> {
            PasswordValidatorService service = new PasswordValidatorService();

            boolean result = service.isValid(password);

            if (result != expectedValidate)
                System.out.println(this.getClass().getSimpleName() + "ERRO => " + " Test Password: [\"" + password + "\"] was not the expected result");

            assertEquals(expectedValidate, service.isValid(password));
        });

    }
}