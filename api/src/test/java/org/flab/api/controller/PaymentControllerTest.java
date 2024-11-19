package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/reservations/{reservationId}/payment";
    private final long RESERVATION_ID = 123L;

    @Test
    @DisplayName("결제 검증 요청")
    public void validatePayment() throws Exception {
        // given
        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI + "/validate", RESERVATION_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("결제 완료 요청")
    public void donePayment() throws Exception {
        // given
        // when
        ResultActions resultActions  = mockMvc.perform(patch(BASE_URI + "/done", RESERVATION_ID)
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
}
