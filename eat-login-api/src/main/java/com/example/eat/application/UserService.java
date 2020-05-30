package com.example.eat.application;

import com.example.eat.domain.User;
import com.example.eat.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
                this.userRepository=userRepository;
                this.passwordEncoder=passwordEncoder ;
    }

    public User authenticate(String email,String password){
       User user=userRepository.findByEmail (email)
               .orElseThrow (() -> new EmailNotExistedException (email));
       //PasswordEncoder passwordEncoder=  passwordEncoder();

      if(!passwordEncoder.matches (password, user.getPassword ())){
            throw new PasswordWrongException();
      }
       return user;
    }

    /*private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }*/
}
