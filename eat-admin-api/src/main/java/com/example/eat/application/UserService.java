package com.example.eat.application;

import com.example.eat.domain.User;
import com.example.eat.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll ();
    }

    public User addUser(String email,String password,String name,String phoneNumber){
        User user= User.builder()
                .email(email)
                .password (password)
                .name (name)
                .phoneNumber (phoneNumber)
                .level (100L)
                .active (1L)
                .admin (1L)
                .build();
        return userRepository.save (user);
    }

    public User updateUser(User newer){
        User user= userRepository.findById (newer.getId ()).orElse (null);
                    user.setEmail (newer.getEmail ())
                        .setName (newer.getName ())
                        .setPhoneNumber (newer.getPhoneNumber ())
                        .setLevel (newer.getLevel ());


        return userRepository.save (user);
    }

    public User deleteUser(Long id){
        User user=userRepository.findById (id).orElse (null);
        user.deativate ();
        user.setActive (0L);
        return user;
    }


}
