package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.seat.dto.response.AreaResponse;
import org.flab.api.domain.seat.dto.response.RowResponse;
import org.flab.api.domain.seat.dto.response.SeatListResponse;
import org.flab.api.domain.seat.dto.response.SeatResponse;
import org.flab.api.domain.seat.domain.SeatStatus;

import java.util.ArrayList;
import java.util.List;

public class SeatDummyGenerator {

    public static SeatListResponse generateSeatListDummy() {
        RowResponse row1 = generateRowDummy("A1", 1, 15);
        RowResponse row2 = generateRowDummy("A1", 2, 17);
        RowResponse row3 = generateRowDummy("A1", 2, 17);
        AreaResponse area1 = new AreaResponse(1, "A1", 3, List.of(row1, row2, row3));

        RowResponse row4 = generateRowDummy("A2", 1, 15);
        RowResponse row5 = generateRowDummy("A2", 2, 17);
        RowResponse row6 = generateRowDummy("A2", 2, 17);
        AreaResponse area2 = new AreaResponse(2, "A2", 3, List.of(row4, row5, row6));
        return new SeatListResponse(123, List.of(area1, area2));
    }

    private static RowResponse generateRowDummy(String areaName, int rowIndex, int rowCount) {
        List<SeatResponse> seatList = new ArrayList<>();
        for(int i=1; i < rowCount; i++) {
            seatList.add(new SeatResponse(areaName + "-" + rowIndex+ "-" + i, SeatStatus.AVAILABLE));
        }
        return new RowResponse(rowIndex, seatList.size(), seatList);
    }
}