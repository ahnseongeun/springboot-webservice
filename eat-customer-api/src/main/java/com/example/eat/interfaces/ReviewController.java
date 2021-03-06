package com.example.eat.interfaces;

import com.example.eat.application.ReviewService;
import com.example.eat.domain.Review;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource)
            throws URISyntaxException {
        Claims claims= (Claims) authentication.getPrincipal ();
        String name=claims.get("name",String.class);
        Review review=reviewService.addReview(
                restaurantId,
                name,
                resource.getScore (),
                resource.getDescription ());
        String url = "/restaurants/"+restaurantId+"/reviews/"+review.getId();
        return ResponseEntity.created (new URI (url))
                .body ("{}");
    }

}
