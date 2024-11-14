package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.reservation.domain.ReservationStatus;

@AllArgsConstructor
@Getter
public class ReservationSimpleResponse {
    private long reservationId;
    private String[] orderIds;
    private int ticketCount;
    private ReservationStatus status;
    private long eventId;
    private String eventName;
    private String showDateTime;
    private String cancelEndDateTime;
}
