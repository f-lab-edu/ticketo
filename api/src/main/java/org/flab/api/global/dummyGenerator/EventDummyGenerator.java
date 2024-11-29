package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.category.dto.SubCategoryResponse;
import org.flab.api.domain.event.dto.event.response.EventCategoryResponse;
import org.flab.api.domain.event.dto.event.response.EventImageResponse;
import org.flab.api.domain.event.dto.event.response.EventListResponse;
import org.flab.api.domain.event.dto.event.response.EventSimpleResponse;
import org.flab.api.domain.event.dto.event.response.PlaceResponse;
import org.flab.api.domain.event.dto.event.response.RegionResponse;

import java.util.List;

public class EventDummyGenerator {

    public static EventListResponse generateDummyEventListResponse() {

        SubCategoryResponse sub1 = new SubCategoryResponse(1, "내한공연");
        EventCategoryResponse category1 = new EventCategoryResponse(1, "콘서트", sub1);
        EventImageResponse image1 = new EventImageResponse("//ticketimage.ticketo.com/Play/image/thumbnail/24/24013437_p.gif", "//ticketimage.ticketo.comrz/image/play/events/poster/24/24013437_p_s.jpg");
        PlaceResponse place1 = new PlaceResponse(1, "고양종합운동장 주경기장");
        RegionResponse region1 = new RegionResponse(1, "경기");
        EventSimpleResponse event1 = new EventSimpleResponse(1, "콜드플레이 내한공연", "20241106", "20241228", category1, place1, region1, image1);

        SubCategoryResponse sub2 = new SubCategoryResponse(12, "오리지널");
        EventCategoryResponse category2 = new EventCategoryResponse(2, "뮤지컬", sub2);
        EventImageResponse image2 = new EventImageResponse("//ticketimage.ticketo.com/Play/image/thumbnail/24/24013422_p.gif", "//ticketimage.ticketo.comrz/image/play/events/poster/24/24013422_p_s.jpg");
        PlaceResponse place2 = new PlaceResponse(1, "잠실종합운동장 주경기장");
        RegionResponse region2 = new RegionResponse(1, "서울");
        EventSimpleResponse event2 = new EventSimpleResponse(1, "시카고 내한공연", "20241106", "20241228", category2, place2, region2, image2);

        return new EventListResponse(2, List.of(event1, event2));
    }
}
