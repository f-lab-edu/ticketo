package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShowControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events/{eventId}/shows";

    @Test
    @DisplayName("이벤트 회차 목록 조회 요청")
    public void getShowListResponse() throws Exception {
        // given
        long eventId = 1L;

        // when
        ResponseEntity<ShowListResponse> response = restTemplate.getForEntity(BASE_URI, ShowListResponse.class, eventId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, ShowListResponse.class);
    }

    @Test
    @DisplayName("회차 없는 이벤트 회차 목록 조회 요청")
    public void getShowListResponseWithNoShows() {
        // given
        long eventId = 2L;

        // when
        try {
            restTemplate.getForEntity(BASE_URI, GlobalExceptionHandler.ErrorResponse.class, eventId);
        } catch(CustomException e) {

        // then
            assertEquals(e.getErrorCode(), ErrorCode.EVENT_HAS_NO_SHOW);
        }
    }

    @Test
    @DisplayName("회차 단 건 조회 요청")
    public void getShowResponse() throws Exception {
        // given
        long showId = 12354L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI + "/{showId}",1, showId)
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
