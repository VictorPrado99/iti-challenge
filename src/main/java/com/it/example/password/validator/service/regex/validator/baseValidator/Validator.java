package com.it.example.password.validator.service.regex.validator.baseValidator;

public abstract class Validator extends Thread {

    /* Password, will be set on construction and used for validation. */
    protected final String password;

    /* Variable we need.*/
    protected boolean isValid;

    /* This class is abstract, because the S from SOLID, all his children will have a Single Responsibility,
     *  Will Validate just his own rule. Like if has any blank space, or if has digits or something and etc */
    public Validator(String password) {
        this.password = password;
    }

    /* The service will call this method to check if the password is valid
     *  I thought about put a call of this.isAlive, to check if the Thread have run
     *  Before return the boolean. But I wasn't sure is was a good idea call run here
     *  So was just design choice. */
    public boolean isValid() {
        return isValid;
    }

    /* I extended Thread for simplicity sake when working with the objects in the service
       I could have implemented Runnable, but to just call isValid and checking the result
       I made as a Thread Subclass*/
    @Override
    public void run() {
        passwordAnalyse();
    } //Run just call password Analyse, who will be implemented in subclasses.

    protected abstract void passwordAnalyse();

}
