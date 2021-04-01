package com.it.example.password.validator;

import com.it.example.password.validator.controller.PasswordValidatorApplicationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PasswordValidatorApplicationTests {

    @Autowired
    private PasswordValidatorApplicationController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
