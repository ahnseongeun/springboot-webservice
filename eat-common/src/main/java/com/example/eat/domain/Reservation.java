package com.example.eat.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant","user"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*private Long restaurantId;

    private Long userId;*/

    @ManyToOne
    //@Column(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    //@Column(name = "user_id")
    private User user;

    @Setter
    private String name;

    @NotEmpty
    @Setter
    private String date;

    @NotEmpty
    @Setter
    private String time;

    @NotNull
    @Setter
    private Integer partySize;
}
