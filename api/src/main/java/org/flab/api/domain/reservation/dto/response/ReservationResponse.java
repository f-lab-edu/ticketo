package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.reservation.domain.ReservationStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReservationResponse {
    private String username;
    private boolean canCancel;
    private ReservationStatus reservationStatus;
    private int ordersTotalCount;
    private List<ReservationOrderResponse> orders;
    private ReservationShowResponse show;
    private ReservationPaymentResponse payment;
    private ReservationDeliveryResponse delivery;

}
