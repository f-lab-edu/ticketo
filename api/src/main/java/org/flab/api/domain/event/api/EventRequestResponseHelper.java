package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.dto.price.DiscountPolicyResponse;
import org.flab.api.domain.event.dto.price.EventPriceListResponse;
import org.flab.api.domain.event.dto.price.SeatGradeResponse;
import org.flab.api.domain.event.service.ConcertService;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.domain.event.service.MusicalService;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventRequestResponseHelper {

    private final EventService eventService;
    private final MusicalService musicalService;
    private final ConcertService concertService;

    public EventResponse getEventResponse(String eventType, long eventId) {
        EventType type = EventType.validateEventType(eventType);
        return switch (type) {
            case CONCERT -> convertToEventResponse(concertService.getEvent(eventId));
            case MUSICAL -> convertToEventResponse(musicalService.getEvent(eventId));
        };
    }

    public EventPriceListResponse getEventPriceListResponse(long eventId) {
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
        List<DiscountPolicyResponse> discountPolicyResponseList = new ArrayList<>();
        discountPolicyList.forEach(discountPolicy -> {
            discountPolicyResponseList.add(new DiscountPolicyResponse(discountPolicy, basePrice));
        });
        return discountPolicyResponseList;
    }

    private EventResponse convertToEventResponse(Event event) {
        if(event instanceof Musical musical) {
            return musical.toResponse();
        } else if(event instanceof Concert concert) {
            return concert.toResponse();
        } else {
            throw new CustomException(ErrorCode.INVALID_EVENT_TYPE);
        }
    }
}
