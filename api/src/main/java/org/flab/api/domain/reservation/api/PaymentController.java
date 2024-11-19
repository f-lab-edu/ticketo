package org.flab.api.domain.reservation.api;

import org.flab.api.domain.reservation.dto.response.ReservationResponse;
import org.flab.api.global.dummyGenerator.ReservationDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservations/{reservationId}/payment")
public class PaymentController {

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@PathVariable("reservationId") String reservationId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/done")
    public ResponseEntity<ReservationResponse> donePayment(@PathVariable("reservationId") String reservationId) {
        ReservationResponse response = ReservationDummyGenerator.generateReservationResponse();
        return ResponseEntity.ok(response);
    }
}
