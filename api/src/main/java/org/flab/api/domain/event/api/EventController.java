package org.flab.api.domain.event.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.dto.event.request.EventRequestParams;
import org.flab.api.domain.event.dto.event.request.MembershipRequest;
import org.flab.api.domain.event.dto.event.response.EventListResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.dto.price.EventPriceListResponse;
import org.flab.api.global.common.ListRequestParams;
import org.flab.api.global.dummyGenerator.EventDummyGenerator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRequestResponseHelper helper;

    @GetMapping
    public ResponseEntity<EventListResponse> getEventList(@ModelAttribute ListRequestParams requestParam, @ModelAttribute @Valid EventRequestParams eventRequestParams) {
        PageRequest.of(requestParam.getPage(), requestParam.getPageSize());
        EventListResponse response = EventDummyGenerator.generateDummyEventListResponse();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/types/{eventType}/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable String eventType, @PathVariable long eventId) {
        EventResponse response = helper.getEventResponse(eventType, eventId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}/prices")
    public ResponseEntity<EventPriceListResponse> getEventPriceList(@PathVariable long eventId){
        EventPriceListResponse response = helper.getEventPriceListResponse(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/pre-reservation/register")
    public ResponseEntity<Void> registerPreReservation(@RequestBody MembershipRequest requestBody, @PathVariable String eventId) {
        return ResponseEntity.ok().build();
    }
}
