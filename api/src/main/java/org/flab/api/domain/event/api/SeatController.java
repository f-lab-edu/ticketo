package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.seat.SeatListResponse;
import org.flab.api.domain.event.service.seat.SeatViewService;
import org.flab.api.domain.event.service.seat.ZoneService;
import org.flab.api.domain.event.service.show.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events/{eventId}/shows/{showId}/zones/{zoneId}/seats")
public class SeatController {

    private final ShowService showService;
    private final SeatViewService seatViewService;
    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<SeatListResponse> getSeatListByZone(@PathVariable long eventId, @PathVariable long showId, @PathVariable long zoneId) {
        Show show = showService.getShow(eventId, showId);
        Zone zone = zoneService.getZone(zoneId);
        SeatListResponse response = seatViewService.getSeatListResponse(show, zone);
        return ResponseEntity.ok(response);
    }
}
