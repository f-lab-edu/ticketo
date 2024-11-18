package org.flab.api.domain.event.dto.seat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RowResponse {
    private int rowIndex;
    private int seatCount;
    private List<SeatResponse> seats;
}
