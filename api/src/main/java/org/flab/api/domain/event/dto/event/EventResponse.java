package org.flab.api.domain.event.dto.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.EventType;
import org.flab.api.domain.place.dto.PlaceResponse;
import org.flab.api.domain.region.dto.RegionResponse;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventResponse {
    private Long eventId;
    private String eventName;
    private EventType eventType;
    private ZonedDateTime eventStartDate;
    private ZonedDateTime eventEndDate;
    private Integer runningTime;
    private String description;
    private ZonedDateTime reservationStartDateTime;
    private ZonedDateTime reservationEndDateTime;
    private Boolean hasPreReservation;
    private ZonedDateTime preReservationStartDateTime;
    private ZonedDateTime preReservationEndDateTime;
    private EventCategoryResponse category;
    private PlaceResponse place;
    private RegionResponse region;
    private EventImageResponse image;

}
