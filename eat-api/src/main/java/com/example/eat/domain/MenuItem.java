package com.example.eat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
    @Setter
    private long id;

    @Setter
    private long restaurantId;
    @Setter
    private String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Transient
    private boolean destroy;


   /* public MenuItem(){}
    public MenuItem(String name) {

        this.name=name;
    }
    public String getName(){
        return name;
    }*/
}
