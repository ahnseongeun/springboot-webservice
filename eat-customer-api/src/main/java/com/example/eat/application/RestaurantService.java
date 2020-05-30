package com.example.eat.application;

import com.example.eat.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository,
                             ReviewRepository reviewRepository) {
        this.restaurantRepository=restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant= restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id)); //항상 만들는 것이 아니고 아닐때만 만들어야 되기때문에 () -> 이처리를 해준다.

        List<MenuItem> menuItems= menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews= reviewRepository.findAllByRestaurantId (id);
        restaurant.setReviews(reviews);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants= restaurantRepository.findAll();
        return restaurants;
    }

    public List<Restaurant> getRegionRestaurants(String region) {
        List<Restaurant> restaurants= restaurantRepository.findAllByAddressContaining(region);
        return restaurants;
    }


    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {
        // TODO: update Restaurant
        Restaurant restaurant= restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name,address);
        //Restaurant restaurant=new Restaurant(id,name,address);
        return restaurant;
    }
}
