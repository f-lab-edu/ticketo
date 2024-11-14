package org.flab.api.domain.seat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class SeatReservationRequest
{
    private long showId;
    private List<SeatRequest> seats;

    @Override
    public String toString() {
        return "ReservationCreateRequest{" + "showId=" + showId + ", seats=" + seats + '}';
    }
}