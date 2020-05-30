package com.example.eat.application;

public class PasswordWrongException extends RuntimeException{
    PasswordWrongException(){
        super("password is wrong");
    }
}
