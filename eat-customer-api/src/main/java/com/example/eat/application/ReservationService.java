package com.example.eat.application;

import com.example.eat.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {


    private ReservationRepository reservationRepository;

    private RestaurantRepository restaurantRepository;

    private UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              RestaurantRepository restaurantRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.restaurantRepository=restaurantRepository;
        this.userRepository=userRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name,
                                      String date, String time, Integer partySize){
        Restaurant restaurant=restaurantRepository.findById (restaurantId).orElseThrow (()-> null);
        User user=userRepository.findById (userId).orElseThrow (()-> null);
        Reservation reservation= Reservation.builder()
                .restaurant (restaurant)
                .user (user)
                .name (name)
                .date (date)
                .time (time)
                .partySize (partySize)
                .build();
        return reservationRepository.save (reservation);
    }
}
