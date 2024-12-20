package org.flab.api.domain.event.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.Zone;

import java.util.List;

@AllArgsConstructor
@Getter
public class ZoneResponse {
    private long zoneId;
    private String zoneName;
    private long rows;
    private long cols;
    private List<SeatResponse> seats;

    public ZoneResponse(Zone zone, List<Seat> seats) {
        this.zoneId = zone.getId();
        this.zoneName = zone.getName();
        this.rows = zone.getRows();
        this.cols = zone.getCols();
        this.seats = seats.stream().map(SeatResponse::new).toList();
    }
}
