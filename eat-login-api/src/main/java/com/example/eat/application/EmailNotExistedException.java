package com.example.eat.application;

public class EmailNotExistedException extends RuntimeException{
    EmailNotExistedException(String email){
        super("Email is not Registered"+email);
    }
}
