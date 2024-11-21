package org.flab.api.domain.event.repository;

import org.flab.api.domain.event.domain.Event;
import java.util.Optional;

public interface EventRepositoryCustom {
    Optional<Event> findEventWithCategoryAndParentById(Long eventId);
}
