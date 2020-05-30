package com.example.eat.interfaces;

import com.example.eat.application.MenuItemService;
import com.example.eat.application.RestaurantService;
import com.example.eat.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//@SuppressWarnings("unchecked")
@Slf4j
@CrossOrigin
@Configuration
@RestController
public class RestaurantController {

   /* @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;*/

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/restaurants/all")
    public List<Restaurant> list(){
        List<Restaurant> restaurants= restaurantService.getRestaurants();
        return restaurants;
    }

    ///localhost:8080/restaurants/region?region=Seoul url경로
    @GetMapping("/restaurants/region")
    public List<Restaurant> listRegion(
            @RequestParam("region") String region
    ){
        List<Restaurant> restaurants= restaurantService.getRegionRestaurants(region);
        return restaurants;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> listRegionAndCategory(
            @RequestParam("region") String region
            ,@RequestParam(value="categoryId",required=false) Long categoryId
    ){
        log.info ("->{}",categoryId);
        List<Restaurant> restaurants= restaurantService.getRegionAndCategoryRestaurants(region,categoryId);
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
       Restaurant restaurant= restaurantService.getRestaurant(id);
       restaurant.setMenuItems (menuItemService.getMenuItems (id));
        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        Restaurant restaurant= restaurantService.addRestaurant(Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build());
        URI location = new URI("/restaurants/"+ restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resource){
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }


}
