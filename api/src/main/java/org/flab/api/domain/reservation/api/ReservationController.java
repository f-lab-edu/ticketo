package org.flab.api.domain.reservation.api;

import jakarta.validation.Valid;
import org.flab.api.domain.event.dto.seat.request.SeatSelectRequest;
import org.flab.api.domain.reservation.dto.request.ReservationCreateRequest;
import org.flab.api.domain.reservation.dto.request.ReservationRequestParams;
import org.flab.api.domain.reservation.dto.response.ReservationListResponse;
import org.flab.api.domain.reservation.dto.response.ReservationResponse;
import org.flab.api.global.common.ListRequestParams;
import org.flab.api.global.dummyGenerator.ReservationDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @PostMapping("/seats")
    public ResponseEntity<Void> reserveSeat(@RequestBody SeatSelectRequest requestBody) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationCreateRequest reservationRequest) {
        ReservationResponse response = ReservationDummyGenerator.generateReservationResponse();
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/waiting")
    public ResponseEntity<ReservationResponse> createReservationWaiting(@RequestBody ReservationCreateRequest reservationRequest) {
        ReservationResponse response = ReservationDummyGenerator.generateReservationWaitingResponse();
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String reservationId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReservationListResponse> getReservationList(@ModelAttribute ListRequestParams listRequestParams, @ModelAttribute @Valid ReservationRequestParams params) {
        ReservationListResponse response = ReservationDummyGenerator.generateReservationListResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        ReservationResponse response = ReservationDummyGenerator.generateReservationResponse();
        return ResponseEntity.ok(response);
    }
}
