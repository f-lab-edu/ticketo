package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.reservation.domain.DeliveryStatus;

@AllArgsConstructor
@Getter
public class ReservationDeliveryResponse {
    private long deliveryId;
    private String receiverName;
    private String address;
    private String phoneNumber;
    private DeliveryStatus deliveryStatus;
}
