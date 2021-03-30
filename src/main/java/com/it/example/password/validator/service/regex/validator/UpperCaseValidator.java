package com.it.example.password.validator.service.regex.validator;

/** Class to validate if exist at least a uppercase character in the password */
public class UpperCaseValidator extends Validator{

    public UpperCaseValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[A-Z]");
    }
}
