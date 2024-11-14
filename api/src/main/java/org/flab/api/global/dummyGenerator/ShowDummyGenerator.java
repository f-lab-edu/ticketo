package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.seat.dto.response.RemainSeatResponse;
import org.flab.api.domain.show.dto.ShowListResponse;
import org.flab.api.domain.show.dto.ShowResponse;
import org.flab.api.domain.show.dto.ShowSimpleResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowDummyGenerator {

    public static ShowListResponse generateShowListResponse() {
        List<ShowSimpleResponse> shows = new ArrayList<>();
        shows.add(new ShowSimpleResponse(123, "202412071400", "202412071400", "202412071400"));
        shows.add(new ShowSimpleResponse(124, "202412071400", "202412071400", "202412071400"));
        return new ShowListResponse(shows.size(), shows);
    }

    public static ShowResponse generateShowResponse() {
        List<RemainSeatResponse> seats = new ArrayList<>();
        seats.add(new RemainSeatResponse(1, "VIP석", 12));
        seats.add(new RemainSeatResponse(2, "R석", 4));
        seats.add(new RemainSeatResponse(3, "S석", 1));
        return new ShowResponse(1, "202412071400", "202412071400", "202412071400", seats);
    }
 }
