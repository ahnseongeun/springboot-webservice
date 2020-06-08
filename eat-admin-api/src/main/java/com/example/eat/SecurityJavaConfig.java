package com.example.eat;

import com.example.eat.domain.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private String secret = "12345678901234567890123456789012";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors ().disable ()
                .csrf ().disable ()
                .formLogin ().disable ()
                .headers ().frameOptions ().disable ();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil (secret);
    }
}
