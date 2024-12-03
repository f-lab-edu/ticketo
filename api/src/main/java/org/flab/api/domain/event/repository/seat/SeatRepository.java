package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long>, SeatRepositoryCustom {
}