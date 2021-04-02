package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

/**
 * Class to validate if exist at least a lowercase character in the password
 */
public class LowerCaseValidator extends Validator implements ICheckExistInPassword {

    public LowerCaseValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[a-z]", password);
    }
}
