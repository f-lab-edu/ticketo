package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;

    public List<Seat> getSeatListByShowIdAndStatus(long showId,SeatStatus seatStatus) {
        return seatRepository.findSeatListByShowIdAndStatus(showId, seatStatus);
    }

    public long countSeatByGradeId(List<Seat> seatList, long gradeId) {
        return seatList.stream().filter(seat -> gradeId == seat.getGrade().getId()).count();
    }
}
