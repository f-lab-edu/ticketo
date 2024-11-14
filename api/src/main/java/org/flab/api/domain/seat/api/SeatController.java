package org.flab.api.domain.seat.api;

import org.flab.api.domain.seat.dto.request.SeatReservationRequest;
import org.flab.api.domain.seat.dto.response.SeatListResponse;
import org.flab.api.global.dummyGenerator.SeatDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events/{eventId}/seats")
public class SeatController {

    @GetMapping
    public ResponseEntity<SeatListResponse> getSeatList(@PathVariable String eventId) {
        SeatListResponse response = SeatDummyGenerator.generateSeatListDummy();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> reserveSeat(@RequestParam(value = "areaId", required = false) Long areaId, @RequestBody SeatReservationRequest requestBody, @PathVariable String eventId) {
        return ResponseEntity.ok().build();
    }

}
