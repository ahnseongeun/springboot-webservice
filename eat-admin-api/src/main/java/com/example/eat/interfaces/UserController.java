package com.example.eat.interfaces;
/*
        1. userlist를 보여줘야한다
        2. user를 create 할수 있어야한다.
        3. user를 update 할수 있어야한다.
        4. user를 delete
        5. level=1 사용자, level=2 owner, level=100이면 admin
 */

import com.example.eat.application.UserService;
import com.example.eat.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Transactional
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {
        User user= userService.addUser(
                resource.getEmail (),
                resource.getPassword (),
                resource.getName (),
                resource.getPhoneNumber ()
        );
        String url="/user/"+user.getId ();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }

    @GetMapping("/users")
    public List<User> list(){
        return userService.getUsers ();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody User resource
            ) throws URISyntaxException {


        User user= userService.updateUser(resource);
        String url="/user/"+user.getId ();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }

    @DeleteMapping("/user/{id}")
    public String delete(
            @PathVariable("id") Long id
    ) throws URISyntaxException {
        userService.deleteUser(id);
        return "{}";
    }
}
