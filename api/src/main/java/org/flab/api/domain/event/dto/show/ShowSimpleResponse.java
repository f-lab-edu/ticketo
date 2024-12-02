package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.Show;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShowSimpleResponse {
    private long showId;
    private ZonedDateTime showDateTime;
    private ZonedDateTime reservationStartDateTime;
    private ZonedDateTime reservationEndDateTime;

    public ShowSimpleResponse(Show show) {
      this.showId = show.getId();
      this.showDateTime = show.getShowDateTime();
      this.reservationStartDateTime = show.getReservationPeriod().getStartDateTime();
      this.reservationEndDateTime = show.getReservationPeriod().getEndDateTime();
    }
}
