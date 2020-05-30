package com.example.eat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotEmpty
    private String email;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotEmpty
    private String phoneNumber;

    private String password;

    @Setter
    @NotNull
    private Long level;

    private Long restaurantId;

    private Long admin;

    private Long active;

    private Long restaurantOwner;

    public void deativate() {
        level = 0L;
    }

    public void setRestaurantId(Long restaurantId) {
        this.level = 50L;
        this.restaurantId = restaurantId;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservationList;

    /* public boolean isAdmin() {
        return level = 100;
    }

    public boolean isActive() {
        return level > 0;
    }*/



}
