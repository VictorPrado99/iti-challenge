package com.it.example.password.validator.pojo;

public class PasswordDTO {

    private String password;

    public PasswordDTO(){ }

    public PasswordDTO(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
