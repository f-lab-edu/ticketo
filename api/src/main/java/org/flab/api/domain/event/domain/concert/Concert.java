package org.flab.api.domain.event.domain.concert;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.dto.event.response.ConcertResponse;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CONCERT")
public class Concert extends Event {

    public ConcertResponse toResponse() {
        return ConcertResponse.builder()
                .eventId(super.getId())
                .eventName(super.getName())
                .eventType(super.getType())
                .runningTime(super.getRunningTime())
                .description(super.getDescription())
                .eventStartDate(super.getEventPeriod().getStartDateTime())
                .eventEndDate(super.getEventPeriod().getEndDateTime())
                .reservationStartDateTime(super.getReservationPeriod().getStartDateTime())
                .reservationEndDateTime(super.getReservationPeriod().getEndDateTime())
                .hasPreReservation(super.getHasPreReservation())
                .preReservationStartDateTime(super.getPreReservationPeriod().getStartDateTime())
                .preReservationEndDateTime(super.getReservationPeriod().getEndDateTime())
                .category(super.getCategory().toEventCategoryResponse())
                .place(super.getPlace().toEventPlaceResponse())
                .region(super.getRegion().toEventRegionResponse())
                .image(super.getImage().toEventImageResponse())
                .build();
    }

}
