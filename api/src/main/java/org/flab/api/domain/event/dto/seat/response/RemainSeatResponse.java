package org.flab.api.domain.event.dto.seat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RemainSeatResponse {
    private long gradeId;
    private String gradeName;
    private int count;
}
