package org.flab.api.domain.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.place.dto.PlaceResponse;
import org.flab.api.domain.region.dto.RegionResponse;

@AllArgsConstructor
@Getter
public class EventSimpleResponse {
    private long eventId;
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
    private EventCategoryResponse category;
    private PlaceResponse place;
    private RegionResponse region;
    private EventImageResponse image;
}
