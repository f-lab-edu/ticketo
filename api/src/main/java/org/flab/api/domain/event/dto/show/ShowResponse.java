package org.flab.api.domain.event.dto.show;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.show.Show;
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
    private List<ParticipantResponse> participants;

    public ShowResponse(Show show, List<RemainSeatResponse> remainSeats, List<ParticipantResponse> participantResponseList) {
        this.showId = show.getId();
        this.showDateTime = show.getShowDateTime();
        this.reservationStartDateTime = show.getReservationPeriod().getStartDateTime();
        this.reservationEndDateTime = show.getReservationPeriod().getEndDateTime();
        this.remainSeats = remainSeats;
        this.participants = participantResponseList;
    }
}
