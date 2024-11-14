package org.flab.api.domain.show.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.seat.dto.response.RemainSeatResponse;

import java.util.List;

@AllArgsConstructor
@Getter
public class ShowResponse {
    private long id;
    private String showDateTime;
    private String reservationStartDateTime;
    private String reservationEndDateTime;
    private List<RemainSeatResponse> remainSeats;
}
