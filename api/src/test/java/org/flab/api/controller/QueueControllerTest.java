package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueueControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events/{eventId}/shows/{showId}/queue";
    private final long EVENT_ID = 12345L;
    private final long SHOW_ID = 11234L;
    private static String uuidToken;

    @BeforeAll
    public static void generateUUID() {
        uuidToken = UUID.randomUUID().toString();
    }

    @Test
    @DisplayName("대기열 진입 요청")
    public void enterQueue () throws Exception {
        // given

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI, EVENT_ID, SHOW_ID)
                        .header(HttpHeaders.AUTHORIZATION, uuidToken)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("대기열 상태 조회 요청")
    public void getQueueStatus () throws Exception {
        // given

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI, EVENT_ID, SHOW_ID)
                .header(HttpHeaders.AUTHORIZATION, uuidToken)
        );

        // then
        resultActions.andExpect(status().isOk());
    }
}
