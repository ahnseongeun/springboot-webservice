package com.example.eat.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestaurantTest {

    @Test
    public void creation(){
        //Restaurant restaurant=new Restaurant(1004L,"Bob zip","Seoul");
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getId(),is(1004L));
        assertThat(restaurant.getName(),is("Bob zip"));
        assertThat(restaurant.getAddress(),is("Seoul"));
    }

    @Test
    public void imformation(){
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getImformation(),is("Bob zip in Seoul"));
    }
}