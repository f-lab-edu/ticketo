package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class EventListResponse {
    private int totalCount;
    private List<EventSimpleResponse> events;
}
