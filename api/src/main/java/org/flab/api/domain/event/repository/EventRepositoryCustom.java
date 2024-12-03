package org.flab.api.domain.event.repository;

import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;

import java.util.Optional;

public interface EventRepositoryCustom {
    Optional<EventType> findEventTypeById(long eventId);
    Optional<Event> findEventWithRelationEntity(long eventId);
}
