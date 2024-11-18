package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.reservation.domain.PaymentMethod;
import org.flab.api.domain.reservation.domain.PaymentStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class ReservationPaymentResponse {
    private long paymentId;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private int deliveryCharge;
    private int totalPrice;
    private ZonedDateTime paymentDateTime;
}
