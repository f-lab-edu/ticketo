package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.reservation.dto.request.ReservationCreateRequest;
import org.flab.api.domain.reservation.dto.request.ReservationSeatRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReservationControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/reservations";

    @Test
    @DisplayName("예매 정보 생성 요청")
    public void createReservation() throws Exception {
        // given
        List<ReservationSeatRequest> seatList = new ArrayList<>();
        seatList.add(new ReservationSeatRequest("A1-12-12", 1L, "VIP석", 1L, "일반", 170000, 2000));
        seatList.add(new ReservationSeatRequest("A1-12-13", 1L, "VIP석", 2L, "할인가", 136000, 2000));
        ReservationCreateRequest requestBody = new ReservationCreateRequest(123L, 123L,  seatList);

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(isA(String.class)))
                .andExpect(jsonPath("$.reservationStatus").value(isA(String.class)))
                .andExpect(jsonPath("$.show").isMap())
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.eventName", isA(String.class)))
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.showDateTime", isA(String.class)))
                .andExpect(jsonPath("$.show.placeId", isA(Number.class)))
                .andExpect(jsonPath("$.show.placeName", isA(String.class)))
                .andExpect(jsonPath("$.canCancel", isA(Boolean.class)))
                .andExpect(jsonPath("$.ordersTotalCount", isA(Number.class)))
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders[*].orderId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].seatCode", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].gradeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].gradeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].priceId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].priceName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].price", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].fee", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.payment").isMap())
                .andExpect(jsonPath("$.payment.paymentId", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentStatus", isA(String.class)))
                .andExpect(jsonPath("$.payment.paymentMethod", isA(String.class)))
                .andExpect(jsonPath("$.payment.deliveryCharge", isA(Number.class)))
                .andExpect(jsonPath("$.payment.totalPrice", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentDateTime", isA(String.class)))
                .andExpect(jsonPath("$.delivery").isMap())
                .andExpect(jsonPath("$.delivery.receiverName", isA(String.class)))
                .andExpect(jsonPath("$.delivery.address", isA(String.class)))
                .andExpect(jsonPath("$.delivery.phoneNumber", isA(String.class)));
    }

    @Test
    @DisplayName("예매 대기 생성 요청")
    public void createReservationWaiting() throws Exception {
        // given
        List<ReservationSeatRequest> seatList = new ArrayList<>();
        seatList.add(new ReservationSeatRequest("A1-12-12", 1L, "VIP석", 1L, "일반", 170000, 2000));
        seatList.add(new ReservationSeatRequest("A1-12-13", 1L, "VIP석", 2L, "할인가", 136000, 2000));
        ReservationCreateRequest requestBody = new ReservationCreateRequest(123L, 123L,  seatList);

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI + "/waiting")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(isA(String.class)))
                .andExpect(jsonPath("$.reservationStatus").value(isA(String.class)))
                .andExpect(jsonPath("$.show").isMap())
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.eventName", isA(String.class)))
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.showDateTime", isA(String.class)))
                .andExpect(jsonPath("$.show.placeId", isA(Number.class)))
                .andExpect(jsonPath("$.show.placeName", isA(String.class)))
                .andExpect(jsonPath("$.canCancel", isA(Boolean.class)))
                .andExpect(jsonPath("$.ordersTotalCount", isA(Number.class)))
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders[*].orderId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].seatCode", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].gradeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].gradeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].priceId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].priceName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].price", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].fee", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.payment").isMap())
                .andExpect(jsonPath("$.payment.paymentId", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentStatus", isA(String.class)))
                .andExpect(jsonPath("$.payment.paymentMethod", isA(String.class)))
                .andExpect(jsonPath("$.payment.deliveryCharge", isA(Number.class)))
                .andExpect(jsonPath("$.payment.totalPrice", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentDateTime", isA(String.class)))
                .andExpect(jsonPath("$.delivery").isMap())
                .andExpect(jsonPath("$.delivery.receiverName", isA(String.class)))
                .andExpect(jsonPath("$.delivery.address", isA(String.class)))
                .andExpect(jsonPath("$.delivery.phoneNumber", isA(String.class)));
    }

    @Test
    @DisplayName("예매 상세 조회 요청")
    public void getReservationResponse() throws Exception {
        // given
        long reservationId = 123L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI + "/{reservationId}", reservationId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(isA(String.class)))
                .andExpect(jsonPath("$.reservationStatus").value(isA(String.class)))
                .andExpect(jsonPath("$.show").isMap())
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.eventName", isA(String.class)))
                .andExpect(jsonPath("$.show.showId", isA(Number.class)))
                .andExpect(jsonPath("$.show.showDateTime", isA(String.class)))
                .andExpect(jsonPath("$.show.placeId", isA(Number.class)))
                .andExpect(jsonPath("$.show.placeName", isA(String.class)))
                .andExpect(jsonPath("$.canCancel", isA(Boolean.class)))
                .andExpect(jsonPath("$.ordersTotalCount", isA(Number.class)))
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders[*].orderId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].seatCode", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].gradeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].gradeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].priceId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].priceName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.orders[*].price", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.orders[*].fee", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.payment").isMap())
                .andExpect(jsonPath("$.payment.paymentId", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentStatus", isA(String.class)))
                .andExpect(jsonPath("$.payment.paymentMethod", isA(String.class)))
                .andExpect(jsonPath("$.payment.deliveryCharge", isA(Number.class)))
                .andExpect(jsonPath("$.payment.totalPrice", isA(Number.class)))
                .andExpect(jsonPath("$.payment.paymentDateTime", isA(String.class)))
                .andExpect(jsonPath("$.delivery").isMap())
                .andExpect(jsonPath("$.delivery.receiverName", isA(String.class)))
                .andExpect(jsonPath("$.delivery.address", isA(String.class)))
                .andExpect(jsonPath("$.delivery.phoneNumber", isA(String.class)));
    }

    @Test
    @DisplayName("예매 목록 조회 요청")
    public void getReservationListResponse() throws Exception {
        // given
        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalCount").value(isA(Number.class)))
                .andExpect(jsonPath("$.reservations").isArray())
                .andExpect(jsonPath("$.reservations[*].reservationId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.reservations[*].orderIds").isArray())
                .andExpect(jsonPath("$.reservations[*].orderIds[*]", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.reservations[*].reservationStatus", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.reservations[*].eventId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.reservations[*].eventName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.reservations[*].showDateTime", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.reservations[*].cancelEndDateTime", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.reservations[*].ticketCount", everyItem(isA(Number.class))));
    }

    @Test
    @DisplayName("예매 취소 요청")
    public void deleteReservation() throws Exception {
        // given
        long reservationId = 123L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI + "/{reservationId}", reservationId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }
}
