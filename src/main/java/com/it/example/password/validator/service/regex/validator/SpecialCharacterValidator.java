package com.it.example.password.validator.service.regex.validator;

/** Class to validate if exist at least a special character in the password */
public class SpecialCharacterValidator extends Validator {

    public SpecialCharacterValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[!@#$%^&*()-+]");
    }
}
