package com.it.example.password.validator.service.regex.validator;

/** Class to validate if didn't exist any whitespace in the Password */
public class BlankSpaceValidator extends Validator {

    public BlankSpaceValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse(){
        isValid = !checkExistInPassword("\\s");
    }

}
