package com.example.eat.interfaces;

import com.example.eat.application.MenuItemService;
import com.example.eat.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody List<MenuItem> menuitems){
        List<MenuItem> menuItems = new ArrayList<>(menuitems);
        menuItemService.bulkUpdate(restaurantId,menuItems);
        return "";
    }

    @GetMapping("/restaurants/{restaurantId}/menuitems")
    public List<MenuItem> getMenuitems(
            @PathVariable("restaurantId") Long id){
        return menuItemService.getMenuItems(id);
    }

}
