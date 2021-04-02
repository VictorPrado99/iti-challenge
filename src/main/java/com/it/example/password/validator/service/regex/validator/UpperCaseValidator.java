package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

/**
 * Class to validate if exist at least a uppercase character in the password
 */
public class UpperCaseValidator extends Validator implements ICheckExistInPassword {

    public UpperCaseValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("[A-Z]", password);
    }
}
