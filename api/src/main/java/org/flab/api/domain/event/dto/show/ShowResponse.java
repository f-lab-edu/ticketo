package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.dto.seat.response.RemainSeatResponse;

import java.util.List;

@AllArgsConstructor
@Getter
public class ShowResponse {
    private long showId;
    private String showDateTime;
    private String reservationStartDateTime;
    private String reservationEndDateTime;
    private List<RemainSeatResponse> remainSeats;
}
