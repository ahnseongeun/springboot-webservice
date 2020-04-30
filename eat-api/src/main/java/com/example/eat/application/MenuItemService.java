package com.example.eat.application;

import com.example.eat.domain.MenuItem;
import com.example.eat.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository=menuItemRepository;
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
            menuItem.setRestaurantId (restaurantId);
            menuItemRepository.save (menuItem);
        }
    }
}
