package org.flab.api.domain.show.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShowSimpleResponse {
    private long id;
    private String showDateTime;
    private String reservationStartDateTime;
    private String reservationEndDateTime;
}
