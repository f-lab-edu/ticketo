package org.flab.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.event.dto.seat.SeatRequest;
import org.flab.api.domain.event.dto.seat.SeatSelectRequest;
import org.flab.api.domain.reservation.domain.ReservationStatus;
import org.flab.api.domain.reservation.dto.request.ReservationCreateRequest;
import org.flab.api.domain.reservation.dto.request.ReservationSeatRequest;
import org.flab.api.domain.reservation.dto.response.ReservationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ReservationControllerTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URI = "/api/reservations";

    @Test
    @DisplayName("좌석 선택 요청")
    public void getSeatReservation() throws Exception {
        // given
        List<SeatRequest> seatList =  List.of(new SeatRequest("A1-1-2"), new SeatRequest("A1-1-3"));
        SeatSelectRequest requestBody = new SeatSelectRequest(123123L, seatList);

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI + "/seats")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        );

        // then
        resultActions.andExpect(status().isOk());
    }

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
    @DisplayName("예매 정보 생성 요청_testRestTemplate")
    public void createReservationByTestRestTemplate() throws JsonProcessingException {
        // given
        List<ReservationSeatRequest> seatList = new ArrayList<>();
        seatList.add(new ReservationSeatRequest("A1-1-1", 1, "VIP석", 1, "일반", 1700000, 2000));
        seatList.add(new ReservationSeatRequest("A1-1-2", 1, "VIP석", 1, "일반", 1700000, 2000));
        ReservationCreateRequest requestBody = new ReservationCreateRequest(123L, 123L,  seatList);

        // when
        ResponseEntity<ReservationResponse> response = restTemplate.postForEntity(BASE_URI, requestBody, ReservationResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).getUsername().isEmpty());
        assertNotNull(response.getBody().getDelivery());
        assertNotNull(response.getBody().getPayment());
        assertNotNull(response.getBody().getShow());

        assertEquals(response.getBody().getOrders().size(), 2);
        assertEquals(response.getBody().getReservationStatus(), ReservationStatus.RESERVED);

        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, ReservationResponse.class);
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
    @DisplayName("예매 대기 생성 요청_testRestTemplate")
    public void createReservationWaitingByTestRestTemplate() throws JsonProcessingException {
        // given
        List<ReservationSeatRequest> seatList = new ArrayList<>();
        seatList.add(new ReservationSeatRequest("A1-1-1", 1, "VIP석", 1, "일반", 1700000, 2000));
        seatList.add(new ReservationSeatRequest("A1-1-2", 1, "VIP석", 1, "일반", 1700000, 2000));
        ReservationCreateRequest requestBody = new ReservationCreateRequest(123L, 123L,  seatList);

        // when
        ResponseEntity<ReservationResponse> response = restTemplate.postForEntity(BASE_URI + "/waiting", requestBody, ReservationResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).getUsername().isEmpty());
        assertNotNull(response.getBody().getDelivery());
        assertNotNull(response.getBody().getPayment());
        assertNotNull(response.getBody().getShow());

        assertEquals(response.getBody().getOrders().size(), 2);
        assertEquals(response.getBody().getReservationStatus(), ReservationStatus.HOLD);

        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, ReservationResponse.class);
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
