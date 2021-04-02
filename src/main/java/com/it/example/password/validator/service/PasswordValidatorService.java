package com.it.example.password.validator.service;

import com.it.example.password.validator.service.regex.validator.*;
import com.it.example.password.validator.service.regex.validator.baseValidator.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordValidatorService {

    /**
     * Main microservice method, the way who was built, can easily be converted to serverless
     */
    public boolean isValid(String password) {
        return passwordAnalyse(password);
    }

    /** This method, will analyse the password passed with subprocesses */
    private boolean passwordAnalyse(String password) {
        /* Validator is a Abstract Class, who extends Thread, this list is just for synchronization sake
         *  I choose to use a simple list, and not actually tools to control multi thread
         *  just because is simpler to get the data from the objects*/
        List<Validator> listThreads = new ArrayList<>();

        /* The simplest rule first, to safeguard processing, if didn't match the
         *  length, is not necessary to continue for regex validation. */
        boolean isValid = passwordLengthRule(password);

        //If has at least 9 characters
        if (isValid) {
            /* I created the ThreadList as Interface so, I can pass the list as reference,
               and abstraction is always good */
            feedThreadService(listThreads, password);

            for (Validator validator :
                    listThreads) {
                try {
                    validator.join(); //Probably the validation was already made, but just to be sure

                    /* The InterruptedException was made here, because I was thinking in implement the validator
                     *  in cases of failure, could interrupt this thread, to end all other threads and just return false
                     *  I didn't implement that, because I didn't see as needed, but was possible if was a really long task */
                } catch (InterruptedException interruptedException) {
                    if (validator.isValid())
                        continue;
                } finally {
                    /* We look if the thread return as valid. Here is a perfect implementation of L from SOLID, we use the Validator,
                     *  who is the superclass from the actual object to retrieve the data, we can use the super without any issue */
                    isValid = validator.isValid();
                }

                if (!isValid) { //If was not valid, interrupt any running thread if necessary, and break the loop, returning as false.
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

    /** Password Length validation */
    private boolean passwordLengthRule(CharSequence password){
        return password.length() >= 9;
    }

    /** Create the list with all validator who will be used. */
    private void feedThreadService(List<Validator> listThread, String password) {

        /* Here I choose don't use injection, just instantiate the validator and use normally. */
        listThread.add(new BlankSpaceValidator(password));
        listThread.add(new DigitValidator(password));
        listThread.add(new LowerCaseValidator(password));
        listThread.add(new RepeatingValidator(password));
        listThread.add(new SpecialCharacterValidator(password));
        listThread.add(new UpperCaseValidator(password));

        startSubServices(listThread);

    }

    /**
     * Start the validators as separated Threads.
     */
    private void startSubServices(List<Validator> listThread) {
        /*A simple interaction just to start the Threads. That way all validation is made in parallel,
        for performance sake*/
        for (Validator validator :
                listThread)
            validator.start();
    }
}
