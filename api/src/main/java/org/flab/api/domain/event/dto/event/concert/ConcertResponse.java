package org.flab.api.domain.event.dto.event.concert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.Concert;
import org.flab.api.domain.event.dto.event.EventCategoryResponse;
import org.flab.api.domain.event.dto.event.EventImageResponse;
import org.flab.api.domain.event.dto.event.EventResponse;
import org.flab.api.domain.event.dto.event.PlaceResponse;
import org.flab.api.domain.event.dto.event.RegionResponse;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ConcertResponse extends EventResponse {

    private List<ArtistResponse> artists;

    public ConcertResponse(Concert concert) {
        super(concert.getId(), concert.getName(), concert.getType(), concert.getEventPeriod().getStartDateTime(), concert.getEventPeriod().getEndDateTime(),
                concert.getRunningTime(), concert.getDescription(), concert.getReservationPeriod().getStartDateTime(), concert.getReservationPeriod().getEndDateTime(),
                concert.getHasPreReservation(), concert.getNullablePeriod().getStartDateTime(), concert.getNullablePeriod().getEndDateTime(),
                new EventCategoryResponse(concert.getCategory()), new PlaceResponse(concert.getPlace()),
                new RegionResponse(concert.getRegion()), new EventImageResponse(concert.getImage())
        );

        this.artists = concert.getArtistList().stream()
                .map(ArtistResponse::new)
                .toList();
    }
}
