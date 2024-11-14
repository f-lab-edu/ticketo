package org.flab.api.domain.seat.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SeatRequest {
    private String seatCode;

    @Override
    public String toString() {
        return "SeatRequest{" + "seatId='" + seatCode + '\'' + '}';
    }
}
