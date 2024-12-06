package org.flab.api.domain.event.repository.event;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.event.EventType;

import java.util.Optional;

public interface EventRepositoryCustom {
    Optional<EventType> findEventTypeById(long eventId);
    Optional<Event> findEventWithRelationEntity(long eventId);
}
