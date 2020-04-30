package com.example.eat.domain;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id){
        super("레스토랑 URL을 찾을수 없습니다."+ id);
    }
}
