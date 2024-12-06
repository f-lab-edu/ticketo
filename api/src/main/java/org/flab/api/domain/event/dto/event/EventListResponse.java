package org.flab.api.domain.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class EventListResponse {
    private int totalCount;
    private List<EventSimpleResponse> events;
}
