package com.it.example.password.validator.service;

import com.it.example.password.validator.pojo.PasswordValidatorModel;
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
        List<Validator> listThreads = new ArrayList<Validator>();
        boolean isValid = true;

        //The simplest rule first, to safeguard processing
        isValid = passwordLengthRule(password);

        if(isValid) {
            feedThreadService(listThreads, password);

            a:
            for (Validator validator :
                    listThreads)
            {
                try {
                    validator.join();
                }catch (InterruptedException interruptedException){
                    isValid = false;
                    break;
                } finally {
                    isValid = validator.isValid();

                    if(!isValid){
                        for (Validator val1:
                             listThreads) {
                            if(!val1.isInterrupted())
                                val1.interrupt();
                        }
                        break a;
                    }
                }
            }
        }

        return isValid;
    }

    //CharSequence, for abstraction passing as reference for memory safety
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
