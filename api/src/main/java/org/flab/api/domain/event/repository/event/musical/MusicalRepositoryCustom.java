package org.flab.api.domain.event.repository.event.musical;

import org.flab.api.domain.event.domain.event.musical.Musical;

import java.util.Optional;

public interface MusicalRepositoryCustom {
    Optional<Musical> findMusicalWithRelationEntity(long eventId);
}
