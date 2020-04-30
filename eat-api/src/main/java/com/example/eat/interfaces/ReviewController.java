package com.example.eat.interfaces;

import com.example.eat.application.ReviewService;
import com.example.eat.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource)
            throws URISyntaxException {
        Review review=reviewService.addReview(restaurantId,resource);
        String url = "/restaurants/"+restaurantId+"/reviews/"+review.getId();
        return ResponseEntity.created (new URI (url))
                .body ("{}");
    }

}
