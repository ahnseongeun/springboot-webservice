package com.example.eat.application;

import com.example.eat.domain.Review;
import com.example.eat.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

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
    public void getReviews(){
        List<Review> mockReviews =new ArrayList<>();
        mockReviews.add(Review.builder ().description ("cool").build ());
        given(reviewRepository.findAll()).willReturn (mockReviews);

        List<Review> reviews=reviewService.getReviews();
        Review review=reviews.get (0);
        assertThat(review.getDescription (),is("cool"));

    }




}