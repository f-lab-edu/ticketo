package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatList getSeatListByShowIdAndStatus(long showId, SeatStatus seatStatus) {
        return new SeatList(seatRepository.findSeatListByShowIdAndStatus(showId, seatStatus));
    }
}
