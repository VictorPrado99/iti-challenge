package com.it.example.password.validator.service.regex.validator;

/** Class to validate if exist at least a digit in the password */
public class DigitValidator extends Validator{

    public DigitValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse(){
        isValid = checkExistInPassword("\\d");
    }

}
