package org.flab.api.domain.event.repository;

import org.flab.api.domain.event.domain.concert.Concert;

import java.util.Optional;

public interface ConcertRepositoryCustom {
    Optional<Concert> findConcertWithRelationEntity(long eventId);
}
