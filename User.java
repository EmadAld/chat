package com.example.pcc.chatting;

import java.io.Serializable;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    private String user ,email ,password,message;
    // boolean signed_in;
    //string macAdress;

    String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String getEmail() {
        return email;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    void printUser(){
        System.out.println("\n      id       : "+id+
                "\n      name     : "+user+
                "\n      Email    : "+email+
                "\n      password : "+password);
        System.out.println("\n _____________________________");
    }



}
