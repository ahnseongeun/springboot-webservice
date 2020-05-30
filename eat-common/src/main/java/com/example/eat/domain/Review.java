package com.example.eat.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant"})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Setter
    private Long restaurantId;*/

    @ManyToOne
   // @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    //@NotEmpty
    private String name;

    @NotNull
    private Integer score;

    @NotEmpty
    private String description;





}
