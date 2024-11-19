package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShowControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events/{eventId}/shows";
    private final long EVENT_ID = 12354L;

    @Test
    @DisplayName("회차 목록 조회 요청")
    public void getShowListResponse() throws Exception {
        // given
        long eventId = 12354L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI, EVENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalCount").value(isA(Number.class)))
                .andExpect(jsonPath("$.shows").isArray())
                .andExpect(jsonPath("$.shows[*].showId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.shows[*].showDateTime", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.shows[*].reservationStartDateTime", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.shows[*].reservationStartDateTime", everyItem(isA(String.class))));
    }

    @Test
    @DisplayName("회차 단 건 조회 요청")
    public void getShowResponse() throws Exception {
        // given
        long showId = 12354L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI + "/{showId}", EVENT_ID, showId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.showId").value(isA(Number.class)))
                .andExpect(jsonPath("$.showDateTime", isA(String.class)))
                .andExpect(jsonPath("$.reservationStartDateTime", isA(String.class)))
                .andExpect(jsonPath("$.reservationStartDateTime", isA(String.class)))
                .andExpect(jsonPath("$.remainSeats").isArray())
                .andExpect(jsonPath("$.remainSeats[*].gradeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.remainSeats[*].gradeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.remainSeats[*].count", everyItem(isA(Number.class))));
    }
}
