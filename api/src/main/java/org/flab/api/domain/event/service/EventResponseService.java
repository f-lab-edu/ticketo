package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.dto.event.response.CharacterResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class EventResponseService {

    private final EventService eventService;
    private final CastResponseService castResponseService;

    public EventResponse getEventResponse(Long eventId) {
        Event event = eventService.getEvent(eventId);
        return convertToResponse(event);
    }

    private EventResponse convertToResponse(Event event) {
        if(event instanceof Musical musical) {
            List<CharacterResponse> castResponseList = castResponseService.getMusicalCastResponseListByCharacter(musical.getId());
            return musical.toResponse(castResponseList);
        } else if(event instanceof Concert concert) {
            return concert.toResponse();
        } else {
            throw new CustomException(ErrorCode.EVENT_TYPE_NOT_FOUND);
        }
    }
}
