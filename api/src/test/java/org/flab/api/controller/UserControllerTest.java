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

public class UserControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("로그인한 사용자 정보 조회 요청")
    public void getMeResponse() throws Exception {
        // given
        String baseUri = "/api/me";

        // when
        ResultActions resultActions  = mockMvc.perform(get(baseUri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(isA(Number.class)))
                .andExpect(jsonPath("$.loginId").value(isA(String.class)))
                .andExpect(jsonPath("$.userName").value(isA(String.class)))
                .andExpect(jsonPath("$.phoneNumber").value(isA(String.class)))
                .andExpect(jsonPath("$.email").value(isA(String.class)))
                .andExpect(jsonPath("$.address").value(isA(String.class)))
                .andExpect(jsonPath("$.grade").value(isA(String.class)))
                .andExpect(jsonPath("$.preReservationEventIds").isArray())
                .andExpect(jsonPath("$.preReservationEventIds[*]", everyItem(isA(Number.class))));
    }
}
