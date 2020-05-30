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
    public List<MenuItem> getMenuItems(Long restaurantId){
        //가짜객체 menuItemRepository에서 id를 찾아서 menuitems로 받아서 돌려준다.
       return menuItemRepository.findAllByRestaurantId(restaurantId);

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
            //TODO: menuItem.setRestaurantId (restaurantId);
            menuItemRepository.save (menuItem);
        }
    }
}
