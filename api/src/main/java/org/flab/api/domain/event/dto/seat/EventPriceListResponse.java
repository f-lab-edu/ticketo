package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class EventPriceListResponse {
    private long totalCount;
    private List<SeatGradeResponse> grades;
}
