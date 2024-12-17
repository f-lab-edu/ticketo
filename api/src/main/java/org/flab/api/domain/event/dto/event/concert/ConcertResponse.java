package org.flab.api.domain.event.dto.event.concert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.Artist;
import org.flab.api.domain.event.domain.event.concert.Concert;
import org.flab.api.domain.event.dto.event.EventCategoryResponse;
import org.flab.api.domain.event.dto.event.EventImageResponse;
import org.flab.api.domain.event.dto.event.EventResponse;
import org.flab.api.domain.place.dto.PlaceResponse;
import org.flab.api.domain.region.dto.RegionResponse;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ConcertResponse extends EventResponse {

    private List<ArtistResponse> artists;

    public ConcertResponse(Concert concert, List<Artist> artistList) {
        super(concert.getId(), concert.getName(), concert.getType(), concert.getEventPeriod().getStartDateTime(), concert.getEventPeriod().getEndDateTime(),
                concert.getRunningTime(), concert.getDescription(), concert.getReservationPeriod().getStartDateTime(), concert.getReservationPeriod().getEndDateTime(),
                concert.getHasPreReservation(),
                concert.getHasPreReservation() ? concert.getPreReservationPeriod().getStartDateTime() : null,
                concert.getHasPreReservation() ? concert.getPreReservationPeriod().getEndDateTime() : null,
                new EventCategoryResponse(concert.getCategory()), new PlaceResponse(concert.getPlace()),
                new RegionResponse(concert.getRegion()), new EventImageResponse(concert.getImage())
        );

        this.artists = artistList.stream()
                .map(ArtistResponse::new)
                .toList();
    }
}
