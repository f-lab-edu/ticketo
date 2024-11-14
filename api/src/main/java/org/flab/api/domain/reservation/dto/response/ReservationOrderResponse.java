package org.flab.api.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationOrderResponse {
    private long orderId;
    private String seatCode;
    private long gradeId;
    private String gradeName;
    private long priceId;
    private String priceName;
    private int price;
    private int fee;
}
