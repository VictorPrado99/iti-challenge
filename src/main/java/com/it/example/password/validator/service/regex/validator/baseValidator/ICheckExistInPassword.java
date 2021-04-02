package com.it.example.password.validator.service.regex.validator.baseValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ICheckExistInPassword {

    /**
     * This Interface exist just because is not all validator, using this code, so is not something
     * common to all regex validators. That way I respect I from SOLID
     * Interface Segregation principle, the subclasses will not have any method who will not use.
     * In that case, is a interface, but we made a physical implementation, because is more like
     * a common method used for different classes. I could have used a static method in a collection class
     * and would got the same results, but I choose that way, so I can force implements this to use.
     */
    default boolean checkExistInPassword(String regex, String password) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }

}
