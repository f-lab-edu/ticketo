package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.EventType;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class EventResponse {
    private Long eventId;
    private String eventName;
    private EventType eventType;
    private ZonedDateTime eventStartDate;
    private ZonedDateTime eventEndDate;
    private Integer runningTime;
    private Integer interMissionTime;
    private String description;
    private ZonedDateTime reservationStartDateTime;
    private ZonedDateTime reservationEndDateTime;
    private Boolean hasPreReservation;
    private ZonedDateTime preReservationStartDateTime;
    private ZonedDateTime preReservationEndDateTime;
    private EventCategoryResponse category;
    private EventPlaceResponse place;
    private EventRegionResponse region;
    private EventImageResponse image;
    private List<EventCastResponse> casts;
}
