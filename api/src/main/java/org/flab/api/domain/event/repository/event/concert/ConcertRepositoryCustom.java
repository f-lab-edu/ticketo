package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.event.concert.Concert;

import java.util.Optional;

public interface ConcertRepositoryCustom {
    Optional<Concert> findConcertWithRelationEntity(long eventId);
}
