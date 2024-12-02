package org.flab.api.domain.event.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.dto.event.request.EventRequestParams;
import org.flab.api.domain.event.dto.event.request.MembershipRequest;
import org.flab.api.domain.event.dto.event.response.ConcertResponse;
import org.flab.api.domain.event.dto.event.response.EventListResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.dto.event.response.musical.MusicalResponse;
import org.flab.api.domain.event.dto.price.DiscountPolicyResponse;
import org.flab.api.domain.event.dto.price.EventPriceListResponse;
import org.flab.api.domain.event.dto.price.SeatGradeResponse;
import org.flab.api.domain.event.service.ConcertService;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.domain.event.service.MusicalService;
import org.flab.api.global.common.ListRequestParams;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final MusicalService musicalService;
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<EventListResponse> getEventList(@ModelAttribute ListRequestParams requestParam, @ModelAttribute @Valid EventRequestParams eventRequestParams) {
        PageRequest.of(requestParam.getPage(), requestParam.getPageSize());
//        EventListResponse response = EventDummyGenerator.generateDummyEventListResponse();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/types/{eventType}/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable String eventType, @PathVariable long eventId) {
        EventResponse response = getEventResponse(eventType, eventId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}/prices")
    public ResponseEntity<EventPriceListResponse> getEventPriceList(@PathVariable long eventId){
        EventPriceListResponse response = getEventPriceListResponse(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/pre-reservation/register")
    public ResponseEntity<Void> registerPreReservation(@RequestBody MembershipRequest requestBody, @PathVariable String eventId) {
        return ResponseEntity.ok().build();
    }

    private EventResponse getEventResponse(String eventType, long eventId) {
        EventType type = EventType.validateEventType(eventType);
        return switch (type) {
            case CONCERT -> convertToEventResponse(concertService.getEvent(eventId));
            case MUSICAL -> convertToEventResponse(musicalService.getEvent(eventId));
        };
    }

    private EventPriceListResponse getEventPriceListResponse(long eventId) {
        Event event = eventService.getEvent(eventId);
        List<SeatGradeResponse> seatGradeResponses = getSeatGradeResponseList(event);
        return new EventPriceListResponse(seatGradeResponses.size(), seatGradeResponses);
    }

    private List<SeatGradeResponse> getSeatGradeResponseList(Event event) {
        List<Grade> gradeList = event.getGradeList();
        List<DiscountPolicy> discountPolicyList = event.getDiscountPolicyList();

        List<SeatGradeResponse> gradeResponseList = new ArrayList<>();
        gradeList.forEach(grade -> {
            List<DiscountPolicyResponse> discountPolicyResponseList = getDiscountPolicyResponseList(grade.getBasePrice(), discountPolicyList);
            SeatGradeResponse gradeResponse = new SeatGradeResponse(grade, discountPolicyResponseList);
            gradeResponseList.add(gradeResponse);

        });
        return gradeResponseList;
    }

    private List<DiscountPolicyResponse> getDiscountPolicyResponseList(long basePrice, List<DiscountPolicy> discountPolicyList) {
        return discountPolicyList.stream().map(discountPolicy -> new DiscountPolicyResponse(discountPolicy, basePrice)).toList();
    }

    private EventResponse convertToEventResponse(Event event) {
        if(event instanceof Musical musical) {
            return new MusicalResponse(musical);
        } else if(event instanceof Concert concert) {
            return new ConcertResponse(concert);
        } else {
            throw new CustomException(ErrorCode.INVALID_EVENT_TYPE);
        }
    }
}
