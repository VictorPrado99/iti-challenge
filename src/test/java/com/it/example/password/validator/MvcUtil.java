package com.it.example.password.validator;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class MvcUtil {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
