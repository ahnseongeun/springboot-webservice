package com.example.eat.interfaces;

import com.example.eat.application.UserService;
import com.example.eat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private User user;

    @PostMapping("/user")
    public ResponseEntity<?> create(
            @RequestBody User resource
            ) throws URISyntaxException {
        if(resource.getRestaurantId ()>=1){
            user=userService.RegisterOwner (
                    resource.getEmail (),
                    resource.getPassword (),
                    resource.getName (),
                    resource.getPhoneNumber (),
                    resource.getRestaurantId ()
            );
        }else {
             user = userService.RegisterUser (
                    resource.getEmail (),
                    resource.getPassword (),
                    resource.getName (),
                    resource.getPhoneNumber ()
            );
        }
        String url="/user/"+user.getId ();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }
}
