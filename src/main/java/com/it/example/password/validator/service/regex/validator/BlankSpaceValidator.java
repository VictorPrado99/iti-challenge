package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.ICheckExistInPassword;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

/**
 * Class to validate if didn't exist any whitespace in the Password
 */
public class BlankSpaceValidator extends Validator implements ICheckExistInPassword {

    public BlankSpaceValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = !checkExistInPassword("\\s", password);
    }

}
