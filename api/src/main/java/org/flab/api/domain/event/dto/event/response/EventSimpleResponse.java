package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventSimpleResponse {
    private long eventId;
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
    private EventCategoryResponse category;
    private EventPlaceResponse place;
    private EventRegionResponse region;
    private EventImageResponse image;
}
