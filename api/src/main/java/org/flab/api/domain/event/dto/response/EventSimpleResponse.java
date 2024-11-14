package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventSimpleResponse {
    private long id;
    private String name;
    private String eventStartDate;
    private String eventEndDate;
    private EventCategoryResponse category;
    private EventPlaceResponse place;
    private EventRegionResponse region;
    private EventImageResponse image;
}
