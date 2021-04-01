package com.it.example.password.validator.controller;

import com.it.example.password.validator.pojo.PasswordDTO;
import com.it.example.password.validator.pojo.PasswordValidatorModel;
import com.it.example.password.validator.service.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordValidatorApplicationController {

    @Autowired
    private PasswordValidatorService passwordValidatorService;

    @RequestMapping(method = RequestMethod.POST, path = "/validPassword")
    public PasswordValidatorModel isValidPassword(@RequestBody PasswordDTO password){
        PasswordValidatorModel passwordValidatorModel = null;

        boolean isValid = false;

        if(password != null) {
            isValid = passwordValidatorService.isValid(password.getPassword());

        }

        return new PasswordValidatorModel(isValid);
    }

}
