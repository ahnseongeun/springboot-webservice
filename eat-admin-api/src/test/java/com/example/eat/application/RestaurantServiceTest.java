package com.example.eat.application;

import com.example.eat.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
       // mockMenuItemRepository();
       // mockReviewRepository();
        //restaurantRepository=new RestaurantRepositoryImpl();
        restaurantService= new RestaurantService(restaurantRepository);
        //menuItemRepository=new MenuItemRepositoryImpl();
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants=new ArrayList<>();
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }

    /*private void mockMenuItemRepository() {
        List<MenuItem> menuItems= new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockReviewRepository() {
        List<Review> reviews=new ArrayList<> ();
        reviews.add (Review.builder()
                .name("BeRyong")
                .score (1)
                .description ("Bad")
                .build ());
        given (reviewRepository.findAllByRestaurantId (1004L)).willReturn(reviews);
    }*/

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant= restaurantService.getRestaurant(1004L);
        //verify(menuItemRepository).findAllByRestaurantId (eq(1004L));
       // verify(reviewRepository).findAllByRestaurantId (eq(1004L));
        assertThat(restaurant.getId(),is(1004L));
       /* MenuItem menuItem= restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(),is("Kimchi"));

        Review review=restaurant.getReviews ().get (0);
        assertThat(review.getDescription (),is("Bad"));*/
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted(){
       restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants= restaurantService.getRestaurants();
        Restaurant restaurant=restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }
    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant= invocation.getArgument(0);
            restaurant.setId(1004L);
            return restaurant;
        });
        Restaurant restaurant= Restaurant.builder()
                .name("BeRyong")
                .address("BeRyong")
                .build();
        /*
        Restaurant saved= Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("BeRyong")
                .build();
        */
        //given(restaurantRepository.save(any())).willReturn(saved);
        Restaurant created= restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(), is(1004L));
    }
    @Test
    public void updateRestaurant(){
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(
                1004L,"sool zip","Busan");

        assertThat(restaurant.getName(),is("sool zip"));
        assertThat(restaurant.getAddress(),is("Busan"));
    }

}