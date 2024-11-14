package org.flab.api.domain.seat.dto.response;

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
