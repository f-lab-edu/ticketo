package org.flab.api.domain.event.api;

import org.flab.api.domain.event.dto.seat.SeatListResponse;
import org.flab.api.global.dummyGenerator.SeatDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/events/{eventId}/seats")
public class SeatController {

    @GetMapping
    public ResponseEntity<SeatListResponse> getSeatList(@RequestParam(value = "areaId", required = false) Long areaId, @PathVariable String eventId) {
        SeatListResponse response = SeatDummyGenerator.generateSeatListDummy();
        if(areaId != null) {
            response = SeatDummyGenerator.generateSeatListInAreaDummy("A3");
        }
        return ResponseEntity.ok(response);
    }
}
