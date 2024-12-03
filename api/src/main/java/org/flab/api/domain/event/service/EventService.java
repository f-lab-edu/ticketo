package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public EventType getTypeById(long eventId) {
        EventType type = eventRepository.findEventTypeById(eventId).orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
        return  EventType.validateEventType(type.name());
    }

    public Event getEvent(long eventId) {
        Optional<Event> event = eventRepository.findEventWithRelationEntity(eventId);
        return event.orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }
}
