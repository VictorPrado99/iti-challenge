package com.it.example.password.validator.service.regex.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator extends Thread {

    protected final String password;

    protected boolean isValid;

    public Validator(String password){
        this.password = password;
    }

    public boolean isValid(){
        return isValid;
    }

    @Override
    public void run(){
        passwordAnalyse();
    }

    protected abstract void passwordAnalyse();

    protected boolean checkExistInPassword(String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
