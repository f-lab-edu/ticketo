package org.flab.api.domain.event.repository.musical;

import org.flab.api.domain.event.domain.musical.Musical;

import java.util.Optional;

public interface MusicalRepositoryCustom {
    Optional<Musical> findMusicalWithRelationEntity(long eventId);
}
