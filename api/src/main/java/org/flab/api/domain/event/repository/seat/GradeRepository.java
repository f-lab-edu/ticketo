package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByEventId(Long eventId);
}
