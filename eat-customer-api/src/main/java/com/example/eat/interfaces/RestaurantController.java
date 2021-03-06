package com.example.eat.interfaces;

import com.example.eat.application.RestaurantService;
import com.example.eat.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SuppressWarnings("unchecked")

@CrossOrigin
@RestController
public class RestaurantController {

  /* @Autowired
   private RestaurantRepository restaurantRepository;

     @Autowired
     private MenuItemRepository menuItemRepository;*/

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/all")
    public List<Restaurant> list(){
        List<Restaurant> restaurants= restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> listRegion(
            @RequestParam("region") String region
    ){
        List<Restaurant> restaurants= restaurantService.getRegionRestaurants(region);
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
       Restaurant restaurant= restaurantService.getRestaurant(id);
        /*
        Restaurant restaurant= (Restaurant) restaurantRepository.findById(id);
        List<MenuItem> menuItems= menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        restaurant.addMenuItem(new MenuItem("Kimchi"));
        */

        return restaurant;
    }

    /*@PostMapping("/restaurants")
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
    }*/


}
