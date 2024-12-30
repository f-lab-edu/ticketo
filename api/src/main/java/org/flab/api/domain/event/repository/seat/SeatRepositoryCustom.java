package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.SeatStatus;

import java.util.List;
import java.util.Map;

public interface SeatRepositoryCustom {
    Map<GradeId,Long> countSeatsByStatusAndShowIdAndGradeIdList(SeatStatus seatStatus, long showId, List<Long> gradeIdList);
}
