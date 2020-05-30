package com.example.eat.interfaces;

import com.example.eat.application.MenuItemService;
import com.example.eat.application.RestaurantService;
import com.example.eat.application.ReviewService;
import com.example.eat.domain.MenuItem;
import com.example.eat.domain.Restaurant;
import com.example.eat.domain.RestaurantNotFoundException;
import com.example.eat.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private MenuItemService menuItemService;

    @MockBean
    private ReviewService reviewService;

    /*
    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository; //의존성 주입

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;
    */

    @Test
    public void list() throws Exception{
        List<Restaurant> restaurants=new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":1004")
        ))
        .andExpect(content().string(containsString("\"name\":\"JOKER House\"")
        ));
    }

    @Test
    public void detailWithExisted() throws Exception{
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        MenuItem menuItem=MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review=Review.builder ()
                .name ("JOKER")
                .score (5)
                .description ("Great!")
                .build ();
        restaurant.setReviews(Arrays.asList (review));

   /*     Restaurant restaurant2=Restaurant.builder()
                .id(2020L)
                .name("Cyber food")
                .address("Seoul")
                .build();*/

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        //given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")
                ))
                .andExpect(content().string(containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(containsString("Kimchi")
                ))
                .andExpect (content ().string (containsString ("Great!")
                ))
        ;

       /* mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")
                ))
                .andExpect(content().string(containsString("\"name\":\"Cyber food\"")
                ));*/

    }
    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

   /* @Test
    public void createWithInvalidData() throws Exception{
        //아래에 있는 주석 코드 처럼 test에서 검사를 하기위해서는 아주 많은 양의 테스트 코드가 필요하지만
        //vaild를 사용하면 test코드를 아주 간단하게 테스트 할수 있다.
        *//*
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant= invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .address(restaurant.getAddress())
                    .name(restaurant.getName())
                    .build();
        });
        *//*

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createWithValidData() throws Exception{
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant= invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .address(restaurant.getAddress())
                    .name(restaurant.getName())
                    .build();
        });
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));
        verify(restaurantService).addRestaurant(any());

    }


    @Test
    public void update() throws Exception{
        mvc.perform(patch("/restaurants/1004") //1004를 수정할거야
                .contentType(MediaType.APPLICATION_JSON) //json형식으로
                .content("{\"name\":\"JOKER Bar\",\"address\":\"Busan\"}") //이렇게
        )
                .andExpect(status().isOk());
        verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");

    }

    @Test
    public void InvaildUpdate1() throws Exception{
        mvc.perform(patch("/restaurants/1004") //1004를 수정할거야
                .contentType(MediaType.APPLICATION_JSON) //json형식으로
                .content("{\"name\":\"\",\"address\":\"\"}") //이렇게
        )
                .andExpect(status().isBadRequest()); //400이라는 오류는 성공
        //verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");

    }

    @Test
    public void InvaildUpdateWithoutName() throws Exception{
        mvc.perform(patch("/restaurants/1004") //1004를 수정할거야
                .contentType(MediaType.APPLICATION_JSON) //json형식으로
                .content("{\"name\":\"\",\"address\":\"Seoul\"}") //이렇게
        )
                .andExpect(status().isBadRequest()); //200이라는 오류는 성공
        //verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");

    }*/
}