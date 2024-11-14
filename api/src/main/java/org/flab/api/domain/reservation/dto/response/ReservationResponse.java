package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.reservation.domain.ReservationStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReservationResponse {
    private String username;
    private ReservationStatus status;
    private boolean canCancel;
    private ReservationShowResponse show;
    private int ordersTotalCount;
    private List<ReservationOrderResponse> orders;
    private ReservationPaymentResponse payment;
    private ReservationDeliveryResponse delivery;

}
