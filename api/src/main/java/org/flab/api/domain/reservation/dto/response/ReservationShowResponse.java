package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationShowResponse {
    private long showId;
    private String eventName;
    private String showDateTime;
    private long placeId;
    private String placeName;
}
