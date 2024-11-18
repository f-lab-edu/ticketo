package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.reservation.domain.DeliveryStatus;
import org.flab.api.domain.reservation.domain.PaymentMethod;
import org.flab.api.domain.reservation.domain.PaymentStatus;
import org.flab.api.domain.reservation.domain.ReservationStatus;
import org.flab.api.domain.reservation.dto.response.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDummyGenerator {

    public static ReservationListResponse generateReservationListResponse() {
        List<ReservationSimpleResponse> reservations = new ArrayList<>();
        reservations.add(new ReservationSimpleResponse(1, new String[]{"12312321", "12321321"}, 2, ReservationStatus.RESERVED, 1232,"킹키부츠", "202412011700", "202412029343"));
        reservations.add(new ReservationSimpleResponse(2, new String[]{"12312323", "12324321"}, 2, ReservationStatus.RESERVED, 1232,"킹키부츠", "202412011700", "202412029343"));
        return new ReservationListResponse(2, reservations);
    }

    public static ReservationResponse generateReservationResponse() {
        ReservationShowResponse show = new ReservationShowResponse(1, "킹키부츠", "202412011700", 123, "인천");
        List<ReservationOrderResponse> orders = new ArrayList<>();
        orders.add(new ReservationOrderResponse(123, "A1-1-1", 1, "VIP석", 1, "일반", 1700000, 2000));
        orders.add(new ReservationOrderResponse(124, "A1-1-2", 1, "VIP석", 1, "일반", 1700000, 2000));
        ReservationPaymentResponse payment = new ReservationPaymentResponse(1, PaymentStatus.PENDING, PaymentMethod.BANK_TRANSFER, 3200, 227200, ZonedDateTime.now());
        ReservationDeliveryResponse delivery = new ReservationDeliveryResponse(1, "홍길동", "서울특별시 관악구", "010-1234-1234", DeliveryStatus.DONE);
        return new ReservationResponse("홍길동", true, ReservationStatus.RESERVED, orders.size(), orders, show, payment, delivery);
    }

    public static ReservationResponse generateReservationWaitingResponse() {
        ReservationShowResponse show = new ReservationShowResponse(1, "킹키부츠", "202412011700", 123, "인천");
        List<ReservationOrderResponse> orders = new ArrayList<>();
        orders.add(new ReservationOrderResponse(123, "A1-1-1", 1, "VIP석", 1, "일반", 1700000, 2000));
        orders.add(new ReservationOrderResponse(124, "A1-1-2", 1, "VIP석", 1, "일반", 1700000, 2000));
        ReservationPaymentResponse payment = new ReservationPaymentResponse(1, PaymentStatus.PENDING, PaymentMethod.BANK_TRANSFER, 3200, 227200, ZonedDateTime.now());
        ReservationDeliveryResponse delivery = new ReservationDeliveryResponse(1, "홍길동", "서울특별시 관악구", "010-1234-1234", DeliveryStatus.DONE);
        return new ReservationResponse("홍길동", true, ReservationStatus.HOLD, orders.size(), orders, show, payment, delivery);
    }
}
