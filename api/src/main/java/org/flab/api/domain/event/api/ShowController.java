package org.flab.api.domain.event.api;

import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.global.dummyGenerator.ShowDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events/{eventId}/shows")
public class ShowController {

    @GetMapping
    public ResponseEntity<ShowListResponse> getShowList(@PathVariable("eventId") long eventId) {
        ShowListResponse response = ShowDummyGenerator.generateShowListResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ShowResponse> getShow(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId) {
        ShowResponse response = ShowDummyGenerator.generateShowResponse();
        return ResponseEntity.ok(response);
    }
}
