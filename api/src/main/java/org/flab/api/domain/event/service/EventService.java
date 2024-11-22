package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public Event getEvent(Long eventId) {
        Optional<Event> event = eventRepository.findEventWithRelationEntity(eventId);
        if(event.isEmpty()) {
            throw new CustomException(ErrorCode.EVENT_NOT_FOUND);
        }
        return event.get();
    }
}
