package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.concert.Concert;

@AllArgsConstructor
@Getter
public class ConcertResponse extends EventResponse {

    public ConcertResponse(Concert concert) {
        super(concert.getId(), concert.getName(), concert.getType(), concert.getEventPeriod().getStartDateTime(), concert.getEventPeriod().getEndDateTime(),
                concert.getRunningTime(), concert.getDescription(), concert.getReservationPeriod().getStartDateTime(), concert.getReservationPeriod().getEndDateTime(),
                concert.getHasPreReservation(), concert.getPreReservationPeriod().getStartDateTime(), concert.getPreReservationPeriod().getEndDateTime(),
                new EventCategoryResponse(concert.getCategory()), new PlaceResponse(concert.getPlace()),
                new RegionResponse(concert.getRegion()), new EventImageResponse(concert.getImage())
        );
    }
}
