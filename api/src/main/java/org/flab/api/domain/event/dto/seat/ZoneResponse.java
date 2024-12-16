package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ZoneResponse {
    private long zoneId;
    private String zoneName;
    private int rows;
    private int cols;
    private List<SeatResponse> seats;
}
