package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.event.dto.seat.response.RemainSeatResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowDummyGenerator {

    public static ShowResponse generateShowResponse() {
        List<RemainSeatResponse> seats = new ArrayList<>();
        seats.add(new RemainSeatResponse(1, "VIP석", 12));
        seats.add(new RemainSeatResponse(2, "R석", 4));
        seats.add(new RemainSeatResponse(3, "S석", 1));
        return new ShowResponse(1, "202412071400", "202412071400", "202412071400", seats);
    }
 }
