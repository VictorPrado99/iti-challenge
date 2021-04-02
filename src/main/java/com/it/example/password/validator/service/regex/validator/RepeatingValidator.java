package com.it.example.password.validator.service.regex.validator;

import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate if exist the same character more than once
 */
public class RepeatingValidator extends Validator {

    public RepeatingValidator(String password) {
        super(password);
    }

    @Override
    protected void passwordAnalyse() {
        isValid = !isRepeating();
    }

    /* Unique implementation from this subclass, here we can see the O fromm SOLID.
     *  The superclass is closed to changes, but open to extension. */
    private boolean isRepeating() {
        boolean isRepeating = false;
        String[] characters = password.split("");

        a:
        for (String character : characters) {
            Pattern pattern = Pattern.compile(character, Pattern.LITERAL);
            Matcher matcher = pattern.matcher(password);

            for (int i = 1; matcher.find(); i++) {
                //If found more than once, that means they repeated character, so the password is invalid
                if (i > 1) {
                    isRepeating = true;
                    break a;
                }
            }
        }

        return isRepeating;
    }
}
