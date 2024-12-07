package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.repository.event.EventRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public Event getEvent(long eventId) {
      return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }
}
