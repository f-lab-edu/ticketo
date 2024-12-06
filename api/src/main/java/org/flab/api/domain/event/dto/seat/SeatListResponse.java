package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SeatListResponse {
    private long placeId;
    private List<AreaResponse> areas;
}
