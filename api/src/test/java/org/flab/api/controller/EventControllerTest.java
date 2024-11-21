package org.flab.api.controller;

import org.flab.api.BaseIntegrationTest;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.dto.event.request.EventRequestParams;
import org.flab.api.domain.event.dto.event.request.MembershipRequest;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.global.common.ListRequestParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EventControllerTest extends BaseIntegrationTest {

    private final String BASE_URI = "/api/events";

    @Test
    @DisplayName("공연 목록 조회 요청")
    public void getEventListResponse() throws Exception {
        // given
        ListRequestParams params = new ListRequestParams();
        params.setPage(1);
        params.setPageSize(10);
        params.setSearchOption("name");
        params.setSearchValue("킹키");
        EventRequestParams eventParams = new EventRequestParams();
        eventParams.setEventType(EventType.CONCERT);
        eventParams.setRegionId(1234L);

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI)
                .params(convertToMultiValueMap(params, eventParams))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalCount").value(isA(Number.class)))
                .andExpect(jsonPath("$.events").isArray())
                .andExpect(jsonPath("$.events[*].eventId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.events[*].eventName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].eventStartDate", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].eventEndDate", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].category[*].categoryId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.events[*].category[*].categoryName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].category[*].subCategory.subCategoryId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.events[*].category[*].subCategory.subCategoryName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].place[*].placeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.events[*].place[*].placeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].region[*].regionId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.events[*].region[*].regionName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].image[*].thumbnailImageUrl", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.events[*].image[*].posterImageUrl", everyItem(isA(String.class))));
    }

    @Test
    @DisplayName("공연 단 건 조회 요청")
    public void getEventResponse() throws Exception {
        // given
        Long eventId = 2L;

        // when
        ResponseEntity<EventResponse> response = restTemplate.getForEntity(BASE_URI+ "/{eventId}", EventResponse.class, eventId);

        // then
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String jsonString = objectMapper.writeValueAsString(response.getBody());
        objectMapper.readValue(jsonString, EventResponse.class);
    }

    @Test
    @DisplayName("공연 별 좌석 가격 목록 조회 요청")
    public void getEventPriceListResponse() throws Exception {
        // given
        long eventId = 12354L;

        // when
        ResultActions resultActions  = mockMvc.perform(get(BASE_URI+"/{eventId}/prices", eventId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalCount").value(isA(Number.class)))
                .andExpect(jsonPath("$.grades").isArray())
                .andExpect(jsonPath("$.grades[*].gradeId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.grades[*].gradeName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.grades[*].basicPrice", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.grades[*].discountPolicies").isArray())
                .andExpect(jsonPath("$.grades[*].discountPolicies[*].discountPolicyId", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.grades[*].discountPolicies[*].discountPolicyName", everyItem(isA(String.class))))
                .andExpect(jsonPath("$.grades[*].discountPolicies[*].discountRate", everyItem(isA(Number.class))))
                .andExpect(jsonPath("$.grades[*].discountPolicies[*].discountPrice", everyItem(isA(Number.class))));
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
