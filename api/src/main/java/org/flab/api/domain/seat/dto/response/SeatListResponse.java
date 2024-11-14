package org.flab.api.domain.seat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SeatListResponse {
    private long placeId;
    private List<AreaResponse> areas;
}
