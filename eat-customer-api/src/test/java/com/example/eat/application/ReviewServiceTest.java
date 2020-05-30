package com.example.eat.application;

import com.example.eat.domain.Review;
import com.example.eat.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {


    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks (this);
        reviewService=new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
            Review review = Review.builder()
                    .name("JOKER")
                    .score(3)
                    .description("excellent")
                    .build ();
        reviewService.addReview(1004L,review);
        verify(reviewRepository).save(any());
    }
}