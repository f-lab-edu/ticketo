package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.service.ConcertService;
import org.flab.api.domain.event.service.MusicalService;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventRequestResponseHelper {

    private final MusicalService musicalService;
    private final ConcertService concertService;

    public EventResponse getEventResponse(String eventType, long eventId) {
        EventType type = EventType.validateEventType(eventType);
        return switch (type) {
            case CONCERT -> convertToEventResponse(concertService.getEvent(eventId));
            case MUSICAL -> convertToEventResponse(musicalService.getEvent(eventId));
        };
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
