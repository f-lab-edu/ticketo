package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;

import java.util.List;

public interface SeatRepositoryCustom {
    List<Seat> findSeatListByShowIdAndStatus(long showId, SeatStatus status);
}
