package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventRequestResponseHelper {

    private final EventService eventService;

    public EventResponse getEventResponse(long eventId) {
        Event event = eventService.getEvent(eventId);
        return convertToEventResponse(event);
    }

    private EventResponse convertToEventResponse(Event event) {
        if(event instanceof Musical musical) {
            return musical.toResponse();
        } else if(event instanceof Concert concert) {
            return concert.toResponse();
        } else {
            throw new CustomException(ErrorCode.EVENT_TYPE_NOT_FOUND);
        }
    }
}
