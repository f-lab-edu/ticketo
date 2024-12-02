package org.flab.api.domain.event.domain.seat;

import java.util.List;

public class SeatList {

    private final List<Seat> seatList;

    public SeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public long countSeatByGradeId(long gradeId) {
        return seatList.stream().filter(seat -> gradeId == seat.getGrade().getId()).count();
    }
}
