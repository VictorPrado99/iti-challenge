package com.it.example.password.validator.service;

import com.it.example.password.validator.service.regex.validator.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordValidatorService {

    /** Main microservice method */
    public boolean isValid(String password) {
        return passwordAnalyse(password);
    }

    private boolean passwordAnalyse(String password) {
        List<Validator> listThreads = new ArrayList<>();

        //The simplest rule first, to safeguard processing
        boolean isValid = passwordLengthRule(password);

        if(isValid) {
            feedThreadService(listThreads, password);

            for (Validator validator :
                    listThreads) {
                try {
                    validator.join();
                } catch (InterruptedException interruptedException) {
                    if (validator.isValid())
                        continue;
                } finally {
                    isValid = validator.isValid();
                }

                if (!isValid) {
                    for (Validator val1 :
                            listThreads) {
                        if (!val1.isInterrupted())
                            val1.interrupt();
                    }
                    break;
                }
            }
        }

        return isValid;
    }

    private boolean passwordLengthRule(CharSequence password){
        return password.length() >= 9;
    }

    private void feedThreadService(List<Validator> listThread, String password){
        listThread.add(new BlankSpaceValidator(password));
        listThread.add(new DigitValidator(password));
        listThread.add(new LowerCaseValidator(password));
        listThread.add(new RepeatingValidator(password));
        listThread.add(new SpecialCharacterValidator(password));
        listThread.add(new UpperCaseValidator(password));

        for (Validator validator:
             listThread)
            validator.start();

    }
}
