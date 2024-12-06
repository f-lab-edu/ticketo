package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.event.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long>, ConcertRepositoryCustom {
}
