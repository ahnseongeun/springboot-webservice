package com.example.eat.application;

import com.example.eat.domain.MenuItem;
import com.example.eat.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class MenuItemServiceTest {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks (this);
        mockMenuItemRepository();
        //mockMenuItemService();
        menuItemService = new MenuItemService(menuItemRepository);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems= new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }


    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems=new ArrayList<MenuItem>();
        menuItems.add (MenuItem.builder().name ("Kimchi").build());
        menuItems.add (MenuItem.builder().id (12L).name ("Gukkbob").build());
        menuItems.add (MenuItem.builder().id (1004L).destroy(true).build());
        menuItemService.bulkUpdate(1L,menuItems);
        verify(menuItemRepository,times(2)).save(any());
        verify(menuItemRepository,times(1)).deleteById (1004L);
    }
}