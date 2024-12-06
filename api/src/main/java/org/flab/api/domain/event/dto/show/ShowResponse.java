package org.flab.api.domain.event.dto.show;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.event.concert.ArtistResponse;
import org.flab.api.domain.event.dto.event.musical.CastResponse;
import org.flab.api.domain.event.dto.seat.RemainSeatResponse;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowResponse {
    private long showId;
    private ZonedDateTime showDateTime;
    private ZonedDateTime reservationStartDateTime;
    private ZonedDateTime reservationEndDateTime;
    private List<RemainSeatResponse> remainSeats;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CastResponse> casts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ArtistResponse> artists;

    public ShowResponse(Show show, List<RemainSeatResponse> remainSeats, List<CastResponse> casts) {
        this.showId = show.getId();
        this.showDateTime = show.getShowDateTime();
        this.reservationStartDateTime = show.getReservationPeriod().getStartDateTime();
        this.reservationEndDateTime = show.getReservationPeriod().getEndDateTime();
        this.remainSeats = remainSeats;
        this.casts = casts;
    }
}
