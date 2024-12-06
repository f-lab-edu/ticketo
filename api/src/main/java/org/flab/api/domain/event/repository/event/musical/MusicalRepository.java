package org.flab.api.domain.event.repository.event.musical;

import org.flab.api.domain.event.domain.event.musical.Musical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalRepository extends JpaRepository<Musical, Long>, MusicalRepositoryCustom {
}
