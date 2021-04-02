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


    /**
     * The service who will be injected by Spring
     */
    @Autowired
    private PasswordValidatorService passwordValidatorService;


    /**
     * Here I needed to choose a method, I didn't wanted to pass the password in URL.
     * I choose POST because we send a body together, but technically could be any method
     *
     * @param password JSON we receive, converted to PasswordDTO, so the application can work with better
     * @return The object who Spring will convert to JSON
     */
    @RequestMapping(method = RequestMethod.POST, path = "/validPassword")
    public PasswordValidatorModel isValidPassword(@RequestBody PasswordDTO password) {
        PasswordValidatorModel passwordValidatorModel = null;

        boolean isValid = false;

        if (password != null) { //NullCheck, just for sanity sake
            //The real microservice, as the challenge asked, receive a String, and return a boolean
            isValid = passwordValidatorService.isValid(password.getPassword());
        }

        /* Based on return, create a simple POJO, wasn't necessary any strategy like build or something else.
         *  Just a simple object to Spring create the JSON to return */
        return new PasswordValidatorModel(isValid);
    }

}
