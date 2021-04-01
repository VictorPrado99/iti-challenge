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

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class RequestPasswordValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestValidationFromAcceptedPassword() throws Exception {
        Map<String, Boolean> passwordBank = new HashMap<>();

        passwordBank.put("", false);
        passwordBank.put("aa", false);
        passwordBank.put("ab", false);
        passwordBank.put("AAAbbbCc", false);
        passwordBank.put("AbTp9!foo", false);
        passwordBank.put("AbTp9 fok", false);
        passwordBank.put(" AbTp9! fok", false);
        passwordBank.put("AbTp9!foA", false);
        passwordBank.put("Ab*t12", false);
        passwordBank.put("AbTp9!fok", true);
        passwordBank.put("QwerT!@#3sd", true);
        passwordBank.put("ZXcvBG$%54", true);

        for (Map.Entry<String, Boolean> entry : passwordBank.entrySet()) {
            String password = entry.getKey();
            Boolean expectedResult = entry.getValue();

            mockMvc.perform(MockMvcRequestBuilders.post("/validPassword")
                    .content(MvcUtil.asJsonString(new PasswordDTO(password)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().json(MvcUtil.asJsonString(new PasswordValidatorModel(expectedResult))));
        }


    }


}
