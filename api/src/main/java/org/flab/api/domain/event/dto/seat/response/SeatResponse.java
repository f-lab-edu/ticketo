package org.flab.api.domain.event.dto.seat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.seat.SeatStatus;

@AllArgsConstructor
@Getter
public class SeatResponse {
    private String seatCode;
    private SeatStatus seatStatus;

    @Override
    public String toString() {
        return "SeatResponse{" + "seatId='" + seatCode + '\'' + ", status=" + seatStatus + '}';
    }
}
