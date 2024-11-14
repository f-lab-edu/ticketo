package org.flab.api.domain.queue.api;

import org.flab.api.domain.queue.dto.QueueStatusResponse;
import org.flab.api.global.dummyGenerator.QueueStatusDummyGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events/{eventId}/shows/{showId}/queue/{userId}")
public class QueueController {

    @PostMapping()
    public ResponseEntity<Void> addQueue(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId, @PathVariable("userId") long userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<QueueStatusResponse> getQueueStatus(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId, @PathVariable("userId") long userId) {
        QueueStatusResponse response = QueueStatusDummyGenerator.generateQueueStatusDummyResponse();
        return ResponseEntity.ok(response);
    }

}
