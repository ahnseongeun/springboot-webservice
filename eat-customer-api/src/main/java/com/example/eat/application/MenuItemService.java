package com.example.eat.application;

import com.example.eat.domain.MenuItem;
import com.example.eat.domain.MenuItemRepository;
import com.example.eat.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {


    private MenuItemRepository menuItemRepository;

    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository,
                            RestaurantRepository restaurantRepository){
        this.menuItemRepository=menuItemRepository;
        this.restaurantRepository=restaurantRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem: menuItems)
        {
            update (restaurantId, menuItem);
        }
        // TODO: bulk update
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy ()){
            menuItemRepository.deleteById (menuItem.getId ());
            // TODO:detete
            return;
        }else {
            menuItem.setRestaurant (restaurantRepository.findById (restaurantId).orElseThrow (()-> null));
            //menuItem.setRestaurantId (restaurantId);
            menuItemRepository.save (menuItem);
        }
    }
}
