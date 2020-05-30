package com.example.eat.interfaces;


import com.example.eat.application.MenuItemService;
import com.example.eat.application.RegionService;
import com.example.eat.application.RestaurantService;
import com.example.eat.application.ReviewService;
import com.example.eat.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith (SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
// 관리자의 경우 review는 확인만 할수있게 하면 된다.
    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private MenuItemService menuItemService;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RegionService regionService;

    @Test
    public void list() throws Exception{
        List<Review> reviews = new ArrayList<> ();
        reviews.add (Review.builder ().description ("good").build ());
        given(reviewService.getReviews()).willReturn (reviews);
        mvc.perform (get("/reviews/"))
                .andExpect (status ().isOk ())
                .andExpect (content ().string (containsString("good")));
    }


}