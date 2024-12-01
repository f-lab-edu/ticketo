package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.GlobalExceptionHandler;
import org.flab.api.global.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("회차 없는 이벤트, 회차 목록 조회 요청")
    public void getShowListResponseWithNoShows() {
        // given
        long eventId = 3L;

        // when
        try {
            restTemplate.getForEntity(BASE_URI, GlobalExceptionHandler.ErrorResponse.class, eventId);
        } catch(ValidateException e) {
            // then
            assertEquals(e.getErrorCode(), ErrorCode.EVENT_HAS_NO_SHOW);
        }
    }

    @Test
    @DisplayName("회차 단 건 조회 요청")
    public void getShowResponse() throws Exception {
        // given
        long eventId = 2L;
        long showId = 3L;

        // when
        ResponseEntity<ShowResponse> response = restTemplate.getForEntity(BASE_URI + "/{showId}", ShowResponse.class, eventId, showId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, ShowResponse.class);
    }

    @Test
    @DisplayName("이벤트에 존재하지 않는 회차 조회 요청")
    public void getShowResponseWithInvalidEventId() {
        // given
        long eventId = 1L;
        long showId = 3L;

        // when
        try {
            restTemplate.getForEntity(BASE_URI + "/{showId}", GlobalExceptionHandler.ErrorResponse.class, eventId, showId);
        } catch(ValidateException e) {
            // then
            assertEquals(e.getErrorCode(), ErrorCode.INVALID_EVENT_SHOW);
        }
    }
}
