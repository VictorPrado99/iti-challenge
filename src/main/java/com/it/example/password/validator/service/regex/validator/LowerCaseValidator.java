package com.it.example.password.validator.service.regex.validator;

/** Class to validate if exist at least a lowercase character in the password */
public class LowerCaseValidator extends Validator {

    public LowerCaseValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[a-z]");
    }
}
