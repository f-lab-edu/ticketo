package org.flab.api.domain.event.repository.event;

import org.flab.api.domain.event.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
