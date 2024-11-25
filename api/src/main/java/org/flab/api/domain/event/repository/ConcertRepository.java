package org.flab.api.domain.event.repository;

import org.flab.api.domain.event.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long>, ConcertRepositoryCustom {
}
