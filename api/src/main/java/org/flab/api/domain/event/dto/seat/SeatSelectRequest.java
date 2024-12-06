package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class SeatSelectRequest {
    private long showId;
    private List<SeatRequest> seats;

    @Override
    public String toString() {
        return "ReservationCreateRequest{" + "showId=" + showId + ", seats=" + seats + '}';
    }
}
