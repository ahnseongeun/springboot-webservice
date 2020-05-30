package com.example.eat.interfaces;

import com.example.eat.application.ReservationService;
import com.example.eat.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/restaurants/{restaurantId}/reservation")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long restaurantId,
            @Valid @RequestBody Reservation resource //valid는 들어온 값이의 조건이 유효한지 검사한다.
    ) throws URISyntaxException {

        Claims claims= (Claims) authentication.getPrincipal ();
        Long userId= claims.get ("userId",Long.class);
        String name= claims.get("name",String.class);
        Reservation reservation=reservationService.addReservation (
                restaurantId,
                userId,
                name,
                resource.getDate (),
                resource.getTime (),
                resource.getPartySize ()
        );
        String url="/restaurants/"+restaurantId+"/reservation/"+reservation.getId ();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }
}
