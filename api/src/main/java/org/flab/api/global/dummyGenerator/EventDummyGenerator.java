package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.category.dto.SubCategoryResponse;
import org.flab.api.domain.event.dto.response.*;
import org.flab.api.domain.event.dto.response.price.DiscountPolicyResponse;
import org.flab.api.domain.event.dto.response.price.EventPriceListResponse;
import org.flab.api.domain.event.dto.response.price.SeatGradeResponse;

import java.util.List;

public class EventDummyGenerator {

    public static EventPriceListResponse generateDummyEventPriceResponse() {

        DiscountPolicyResponse discount1 = new DiscountPolicyResponse(1, "재관람 할인(1인1매)20%", 20, 136000);
        DiscountPolicyResponse discount2 = new DiscountPolicyResponse(2, "청소년 할인(본인만)20%", 20, 136000);
        discount1.setPrice(170000);
        discount2.setPrice(170000);
        SeatGradeResponse grade1 = new SeatGradeResponse(1, "VIP석", 1700000, List.of(discount1, discount2));

        DiscountPolicyResponse discount3 = new DiscountPolicyResponse(1, "재관람 할인(1인1매)20%", 20, 136000);
        DiscountPolicyResponse discount4 = new DiscountPolicyResponse(2, "청소년 할인(본인만)20%", 20, 136000);
        discount3.setPrice(150000);
        discount4.setPrice(150000);
        SeatGradeResponse grade2 = new SeatGradeResponse(2, "S석", 1500000, List.of(discount3, discount4));

        return EventPriceListResponse.builder()
                .totalCount(2)
                .grades(List.of(grade1, grade2))
                .build();
    }

    public static EventResponse generateDummyEventResponse() {

        EventImageResponse image = new EventImageResponse("//ticketimage.interpark.com/Play/image/large/24/24013437_p.gif", "//ticketimage.interpark.comrz/image/play/goods/poster/24/24013437_p_s.jpg");
        EventCastResponse cast1 = new EventCastResponse(1, "찰리", 4528, "김호영", "\"http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4522_021034.gif");
        EventCastResponse cast2 = new EventCastResponse(1, "찰리", 30078, "신재범", "\"http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4522_021034.gif");

        return EventResponse.builder()
                .eventId(123)
                .eventName("뮤지컬 〈킹키부츠〉 - 광주")
                .eventStartDate("20250123")
                .eventEndDate("20250323")
                .runningTime(155)
                .interMissionTime(20)
                .reservationStartDateTime("202412311700")
                .reservationEndDateTime("202503230000")
                .hasPreReservation(true)
                .preReservationStartDateTime("202412301700")
                .preReservationEndDateTime("202412310000")
                .image(image)
                .casts(List.of(cast1, cast2))
                .bizId(1234)
                .bizInfo("- 제 작 : CJ ENM\\r\\n- 주 최 : KCTV광주방송\\r\\n- 주 관 : ㈜공연마루, ㈜장터미디어")
                .build();
    }


    public static EventListResponse generateDummyEventListResponse() {

        SubCategoryResponse sub1 = new SubCategoryResponse(1, "내한공연");
        EventCategoryResponse category1 = new EventCategoryResponse(1, "콘서트", sub1);
        EventImageResponse image1 = new EventImageResponse("//ticketimage.ticketo.com/Play/image/thumbnail/24/24013437_p.gif", "//ticketimage.ticketo.comrz/image/play/events/poster/24/24013437_p_s.jpg");
        EventPlaceResponse place1 = new EventPlaceResponse(1, "고양종합운동장 주경기장");
        EventRegionResponse region1 = new EventRegionResponse(1, "경기");
        EventSimpleResponse event1 = new EventSimpleResponse(1, "콜드플레이 내한공연", "20241106", "20241228", category1, place1, region1, image1);

        SubCategoryResponse sub2 = new SubCategoryResponse(12, "오리지널");
        EventCategoryResponse category2 = new EventCategoryResponse(2, "뮤지컬", sub2);
        EventImageResponse image2 = new EventImageResponse("//ticketimage.ticketo.com/Play/image/thumbnail/24/24013422_p.gif", "//ticketimage.ticketo.comrz/image/play/events/poster/24/24013422_p_s.jpg");
        EventPlaceResponse place2 = new EventPlaceResponse(1, "잠실종합운동장 주경기장");
        EventRegionResponse region2 = new EventRegionResponse(1, "서울");
        EventSimpleResponse event2 = new EventSimpleResponse(1, "시카고 내한공연", "20241106", "20241228", category2, place2, region2, image2);

        return new EventListResponse(2, List.of(event1, event2));
    }
}
