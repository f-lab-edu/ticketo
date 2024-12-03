package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.event.dto.event.request.EventRequestParams;
import org.flab.api.domain.event.dto.event.request.MembershipRequest;
import org.flab.api.domain.event.dto.event.response.concert.ConcertResponse;
import org.flab.api.domain.event.dto.event.response.musical.MusicalResponse;
import org.flab.api.domain.event.dto.price.EventPriceListResponse;
import org.flab.api.global.common.ListRequestParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EventControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events";

    @Test
    @DisplayName("공연 목록 조회 요청")
    public void getEventListResponse() throws Exception {

    }

    @Test
    @DisplayName("뮤지컬 조회 요청")
    public void getMusicalResponse() throws Exception {
        // given
        long musicalId = 2L;

        // when
        ResponseEntity<MusicalResponse> response = restTemplate.getForEntity(BASE_URI+ "/{musicalId}"
                , MusicalResponse.class, musicalId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, MusicalResponse.class);
    }

    @Test
    @DisplayName("콘서트 조회 요청")
    public void getConcertResponse() throws Exception {
        // given
        long concertId = 1L;

        // when
        ResponseEntity<ConcertResponse> response = restTemplate.getForEntity(BASE_URI+ "/{concertId}"
                , ConcertResponse.class, concertId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, ConcertResponse.class);

    }

    @Test
    @DisplayName("공연 별 좌석 가격 목록 조회 요청")
    public void getEventPriceListResponse() throws Exception {
        // given
        long eventId = 2L;

        // when
        ResponseEntity<EventPriceListResponse> response = restTemplate.getForEntity(BASE_URI+ "/{eventId}/prices"
                , EventPriceListResponse.class, eventId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, EventPriceListResponse.class);
    }

    @Test
    @DisplayName("공연 별 선예매 등록 요청")
    public void registerPreReservation() throws Exception {
        // given
        long eventId = 12354L;
        MembershipRequest membershipRequest = new MembershipRequest("홍길동", "12332434");

        // when
        ResultActions resultActions  = mockMvc.perform(post(BASE_URI+"/{eventId}/pre-reservation/register", eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(membershipRequest))
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    private MultiValueMap<String, String> convertToMultiValueMap(ListRequestParams params, EventRequestParams eventParams) {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("page", String.valueOf(params.getPage()));
        paramMap.add("pageSize", String.valueOf(params.getPageSize()));
        paramMap.add("searchOption", params.getSearchOption());
        paramMap.add("searchValue", params.getSearchValue());
        paramMap.add("category", eventParams.getEventType().toString());
        paramMap.add("regionId", String.valueOf(eventParams.getRegionId()));
        return paramMap;
    }
}
