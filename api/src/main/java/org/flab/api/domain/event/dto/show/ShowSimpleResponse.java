package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShowSimpleResponse {
    private long showId;
    private ZonedDateTime showDateTime;
    private ZonedDateTime reservationStartDateTime;
    private ZonedDateTime reservationEndDateTime;
}
