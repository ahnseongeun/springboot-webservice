package com.example.eat.interfaces;

import com.example.eat.application.UserService;
import com.example.eat.domain.User;
import com.example.eat.domain.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto requestDto
    ) throws URISyntaxException {
        String uri="/session";
        User user=userService.authenticate (requestDto.getEmail (),requestDto.getPassword ());
        String accessToken= jwtUtil.createToken (
                user.getId (),
                user.getName (),
                user.isRestaurantOwner ()?user.getRestaurantId ():null);

        return ResponseEntity.created (new URI (uri)).body (
                SessionResponseDto.builder ()
                        .accessToken (accessToken)
                        .build ());
    }
}
