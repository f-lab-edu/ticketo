package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.dto.event.response.CharacterResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
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
        List<CharacterResponse> castResponseList = castResponseService.getEventCastResponseListByCharacter(eventId);
        return event.toResponse(castResponseList);
    }
}
