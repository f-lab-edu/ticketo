package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SeatControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events/{eventId}/seats";
    private final long EVENT_ID = 1234L;

    @Test
    @DisplayName("좌석 목록 조회 요청")
    public void getSeatListResponse() throws Exception {
        // given
        String areaId = "";

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI, EVENT_ID)
                .param("areaId", areaId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId").value(isA(Number.class)))
                .andExpect(jsonPath("$.areas").isArray())
                .andExpect(jsonPath("$.areas[*].areaId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].areaName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.areas[*].rowCount", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows").isArray())
                .andExpect(jsonPath("$.areas[*].rows[*].rowIndex", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seatCount", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seats").isArray())
                .andExpect(jsonPath("$.areas[*].rows[*].seats[*].seatCode", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seats[*].seatStatus", everyItem(isA(String.class))));
    }

    @Test
    @DisplayName("특정 구역 좌석 목록 조회 요청")
    public void getSeatListInAreaResponse() throws Exception {
        // given
        Long areaId = 123L;
        String areaName = "A3";

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI, EVENT_ID)
                .param("areaId", String.valueOf(areaId))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId").value(isA(Number.class)))
                .andExpect(jsonPath("$.areas").isArray())
                .andExpect(jsonPath("$.areas[*].areaId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].areaName", everyItem(is(areaName))))
                .andExpect(jsonPath("$.areas[*].rowCount", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows").isArray())
                .andExpect(jsonPath("$.areas[*].rows[*].rowIndex", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seatCount", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seats").isArray())
                .andExpect(jsonPath("$.areas[*].rows[*].seats[*].seatCode", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.areas[*].rows[*].seats[*].seatStatus", everyItem(isA(String.class))));
    }
}
