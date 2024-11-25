package org.flab.api.domain.event.repository.musical;

import org.flab.api.domain.event.domain.musical.Musical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalRepository extends JpaRepository<Musical, Long>, MusicalRepositoryCustom {
}
