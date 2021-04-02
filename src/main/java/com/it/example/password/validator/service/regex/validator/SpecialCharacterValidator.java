package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

/**
 * Class to validate if exist at least a special character in the password
 */
public class SpecialCharacterValidator extends Validator implements ICheckExistInPassword {

    public SpecialCharacterValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[!@#$%^&*()-+]", password);
    }
}
