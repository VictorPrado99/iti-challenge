package com.it.example.password.validator.web.request;

import com.it.example.password.validator.MvcUtil;
import com.it.example.password.validator.pojo.PasswordDTO;
import com.it.example.password.validator.pojo.PasswordValidatorModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class RequestPasswordValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestValidationFromAcceptedPassword() throws Exception {
        String password = "";
        boolean expectedResult = false;

        mockMvc.perform(MockMvcRequestBuilders.post("/validPassword")
                .content(MvcUtil.asJsonString(new PasswordDTO(password)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(MvcUtil.asJsonString(new PasswordValidatorModel(expectedResult))));

    }

    @Test
    public void requestValidationFromNotAcceptedPassword() {

    }

}
