package org.flab.api.domain.event.dto.seat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SeatResponse {
    private long seatId;
    private String seatCode;
    private SeatStatus seatStatus;

    public SeatResponse(Seat seat) {
        this.seatId = seat.getId();
        this.seatStatus = seat.getStatus();
        this.seatCode = seat.getZone().getName() + "-" + seat.getRowNumber() + "-" + seat.getColNumber();
    }
}
