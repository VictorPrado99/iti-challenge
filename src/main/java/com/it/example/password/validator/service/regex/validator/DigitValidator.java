package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

/**
 * Class to validate if exist at least a digit in the password
 */
public class DigitValidator extends Validator implements ICheckExistInPassword {

    public DigitValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = checkExistInPassword("\\d", password);
    }

}
