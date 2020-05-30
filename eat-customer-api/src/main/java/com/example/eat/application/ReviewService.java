package com.example.eat.application;

import com.example.eat.domain.Restaurant;
import com.example.eat.domain.RestaurantRepository;
import com.example.eat.domain.Review;
import com.example.eat.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {


    private ReviewRepository reviewRepository;

    private RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,RestaurantRepository restaurantRepository ) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository=restaurantRepository;
    }

    public Review addReview( Long restaurantId,String name,Integer score,
                             String description) {
        Restaurant restaurant=restaurantRepository.findById (restaurantId).orElseGet (null);
        Review review= Review.builder ()
                .restaurant (restaurant)
                .name (name)
                .score (score)
                .description (description)
                .build ();
      return reviewRepository.save(review);
    }
}
