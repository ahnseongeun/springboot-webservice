package com.example.eat.application;

import com.example.eat.domain.Reservation;
import com.example.eat.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {


    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<Reservation> getReservations(Long restaurantId) {

        return reservationRepository.findByRestaurantId (restaurantId);
    }
}
