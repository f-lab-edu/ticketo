package org.flab.api.domain.event.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EventResponse {
    private long eventId;
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
    private int runningTime;
    private int interMissionTime;
    private String description;
    private long bizId;
    private String bizInfo;
    private String reservationStartDateTime;
    private String reservationEndDateTime;
    private boolean hasPreReservation;
    private String preReservationStartDateTime;
    private String preReservationEndDateTime;
    private EventCategoryResponse category;
    private EventPlaceResponse place;
    private EventRegionResponse region;
    private EventImageResponse image;
    private List<EventCastResponse> casts;
}
