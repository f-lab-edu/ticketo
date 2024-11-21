package org.flab.api.domain.event.dto.seat.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeatRequest {
    private String seatCode;

    @Override
    public String toString() {
        return "SeatRequest{" + "seatId='" + seatCode + '\'' + '}';
    }
}
