package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.seat.SeatStatus;

@AllArgsConstructor
@Getter
public class SeatResponse {
    private long seatId;
    private String seatCode;
    private SeatStatus seatStatus;

    public SeatResponse(long seatId, SeatStatus seatStatus, String zoneName , long rowNumber, long colNumber) {
        this.seatId = seatId;
        this.seatStatus = seatStatus;
        private static final String SEAT_CODE_FORMAT = "%s-%s-%s";
        this.seatCode = String.format(SEAT_CODE_FORMAT, zoneName, rowNumber, colNumber);
    }
}
