package org.flab.api.domain.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class ReservationSeatRequest {
    private String seatCode;
    private long gradeId;
    private String gradeName;
    private long priceId;
    private String priceName;
    private int price;
    private int fee;

    @Override
    public String toString() {
        return "ReservationSeatRequest{" + "seatCode='" + seatCode + '\'' + ", gradeId=" + gradeId + ", gradeName='" + gradeName + '\'' + ", priceId=" + priceId + ", priceName='" + priceName + '\'' + ", price=" + price + ", fee=" + fee + '}';
    }
}
