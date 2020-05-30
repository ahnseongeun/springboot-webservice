package com.example.eat.application;

import com.example.eat.domain.User;
import com.example.eat.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public User RegisterUser(String email, String password, String name, String phoneNumber) {
        Optional<User> existed =userRepository.findByEmail (email);
        if(existed.isPresent ()){
            throw new EmailExistedException(email);
        }
       // PasswordEncoder passwordEncoder=passwordEncoder();
        String encodedPassword= passwordEncoder.encode (password);
        User user= User.builder()
                .email (email)
                .password (encodedPassword)
                .name (name)
                .level (1L)
                .active (1L)
                .phoneNumber (phoneNumber)
                .build();
        return userRepository.save (user);
    }

    public User RegisterOwner(String email, String password,
                              String name, String phoneNumber, Long restaurantId) {
        Optional<User> existed =userRepository.findByEmail (email);
        if(existed.isPresent ()){
            throw new EmailExistedException(email);
        }
        // PasswordEncoder passwordEncoder=passwordEncoder();
        String encodedPassword= passwordEncoder.encode (password);
        User user= User.builder()
                .email (email)
                .password (encodedPassword)
                .name (name)
                .phoneNumber (phoneNumber)
                .active (1L)
                .restaurantOwner (1L)
                .build();
        user.setRestaurantId (restaurantId);
        return userRepository.save (user);
    }

    /*public User RegisterOwner(String email, String password, String name, String phoneNumber) {
        Optional<User> existed =userRepository.findByEmail (email);
        if(existed.isPresent ()){
            throw new EmailExistedException(email);
        }
        // PasswordEncoder passwordEncoder=passwordEncoder();
        String encodedPassword= passwordEncoder.encode (password);
        User user= User.builder()
                .email (email)
                .password (encodedPassword)
                .name (name)
                .level (50L)
                .phoneNumber (phoneNumber)
                .build();
        return userRepository.save (user);
    }*/



    /*private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }*/
}
