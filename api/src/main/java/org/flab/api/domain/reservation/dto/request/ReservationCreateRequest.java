package org.flab.api.domain.reservation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReservationCreateRequest {
    @NotEmpty
    private long eventId;
    @NotEmpty
    private long showId;
    @Size(min = 1)
    private List<ReservationSeatRequest> seats;

    @Override
    public String toString() {
        return "ReservationCreateRequest{" + "eventId=" + eventId + ", showId=" + showId + ", seats=" + seats + '}';
    }
}
