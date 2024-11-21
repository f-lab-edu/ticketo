package org.flab.api.domain.event.dto.price;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class EventPriceListResponse {
    private long totalCount;
    private List<SeatGradeResponse> grades;
}
