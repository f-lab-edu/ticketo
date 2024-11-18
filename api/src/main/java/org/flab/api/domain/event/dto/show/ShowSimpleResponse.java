package org.flab.api.domain.event.dto.show;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShowSimpleResponse {
    private long showId;
    private String showDateTime;
    private String reservationStartDateTime;
    private String reservationEndDateTime;
}