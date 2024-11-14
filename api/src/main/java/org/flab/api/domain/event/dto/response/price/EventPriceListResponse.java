package org.flab.api.domain.event.dto.response.price;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EventPriceListResponse {
    private long totalCount;
    private List<SeatGradeResponse> grades;
}
