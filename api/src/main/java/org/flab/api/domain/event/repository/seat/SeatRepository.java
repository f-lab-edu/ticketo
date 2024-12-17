package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    boolean existsByShowId(Long showId);
    Seat findSeatById(Long seatId);
    long countSeatsByStatusAndShowIdAndZoneId(SeatStatus seatStatus, long showId, long zoneId);
}
