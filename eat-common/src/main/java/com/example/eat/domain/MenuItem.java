package com.example.eat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant"})
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter
    private long id;

    //@Setter
    //private long restaurantId;

    @ManyToOne
    //@JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

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
