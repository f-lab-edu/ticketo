package org.flab.api.domain.reservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations/{reservationId}/payment")
public class PaymentController {

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@PathVariable("reservationId") String reservationId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/done")
    public ResponseEntity<Void> donePayment(@PathVariable("reservationId") String reservationId) {
        return ResponseEntity.ok().build();
    }
}
