package org.flab.api.domain.event.dto.event.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.dto.event.response.musical.MusicalResponse;

import java.time.ZonedDateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcertResponse.class, name = "concert"),
        @JsonSubTypes.Type(value = MusicalResponse.class, name = "musical")
})
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
