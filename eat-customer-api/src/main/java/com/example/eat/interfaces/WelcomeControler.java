package com.example.eat.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeControler {

    @GetMapping
    public String hello(){
        return "ㅈㅇㅈ ㄷㅈ" +
                "ㄷㅈ ㅈㅇㅈ"
                +"\n"+"하지만 카레는 존맛탱!!!!";
    }
}
