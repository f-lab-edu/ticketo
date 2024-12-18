package org.flab.api.service;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.flab.api.domain.event.service.seat.SeatCacheService;
import org.flab.api.domain.event.service.seat.SeatService;
import org.flab.api.domain.event.service.seat.ZoneService;
import org.flab.api.domain.place.domain.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ZoneService zoneService;

    @Mock
    private SeatCacheService seatCacheService;

    @InjectMocks
    private SeatService target;

    @Test
    @DisplayName("회차 별 좌석 생성")
    public void generateSeatsForShow() {
        // given
        long showId = 1L;
        long zone1Rows = 10L;
        long zone2Rows = 15L;
        long zone1Cols = 20L;
        long zone2Cols = 20L;

        Zone mockZone1 = mock(Zone.class);
        Zone mockZone2 = mock(Zone.class);
        given(mockZone1.getRows()).willReturn(zone1Rows);
        given(mockZone1.getCols()).willReturn(zone1Cols);
        given(mockZone2.getRows()).willReturn(zone2Rows);
        given(mockZone2.getCols()).willReturn(zone2Cols);

        Place mockPlace = mock(Place.class);
        given(mockPlace.getZoneList()).willReturn(List.of(mockZone1, mockZone2));

        Event mockEvent = mock(Event.class);
        given(mockEvent.getPlace()).willReturn(mockPlace);
        Show mockShow = mock(Show.class);
        given(mockShow.getId()).willReturn(showId);
        given(mockShow.getEvent()).willReturn(mockEvent);

        given(seatCacheService.preparedSeatsForShow(showId)).willReturn(false);

        // when
        List<Seat> seatList = target.generateSeatsForShow(mockShow);

        // then
        long totalSeatCount = zone1Rows * zone1Cols + zone2Rows * zone2Cols;
        assertThat(seatList.size()).isEqualTo(totalSeatCount);
    }

    @Test
    @DisplayName("등급 별 좌석 수를 담은 Map 반환")
    public void getSeatsCountMapTest() {
        // given
        long showId = 1L;
        long placeId = 1L;
        long gradeId1 = 1L, gradeId2 = 2L;
        long zoneId1 = 3L, zoneId2 = 4L, zoneId3 = 5L;
        long count1 = 10L, count2 = 15L, count3= 5L;

        Place mockPlace = mock(Place.class);
        given(mockPlace.getId()).willReturn(placeId);
        Event mockEvent = mock(Event.class);
        given(mockEvent.getPlace()).willReturn(mockPlace);
        Show mockShow = mock(Show.class);
        given(mockShow.getId()).willReturn(showId);
        given(mockShow.getEvent()).willReturn(mockEvent);

        Grade mockGrade1 = mock(Grade.class);
        Grade mockGrade2 = mock(Grade.class);
        Zone mockZone1 = mock(Zone.class);
        Zone mockZone2 = mock(Zone.class);
        Zone mockZone3 = mock(Zone.class);

        given(mockGrade1.getId()).willReturn(gradeId1);
        given(mockGrade2.getId()).willReturn(gradeId2);
        given(mockZone1.getId()).willReturn(zoneId1);
        given(mockZone2.getId()).willReturn(zoneId2);
        given(mockZone3.getId()).willReturn(zoneId3);
        given(mockZone1.getGrade()).willReturn(mockGrade1);
        given(mockZone2.getGrade()).willReturn(mockGrade2);
        given(mockZone3.getGrade()).willReturn(mockGrade2);

        List<Zone> zoneList = List.of(mockZone1, mockZone2, mockZone3);
        given(zoneService.getZoneList(placeId)).willReturn(zoneList);

        given(seatRepository.countSeatsByStatusAndShowIdAndZoneId(SeatStatus.AVAILABLE, showId, zoneId1)).willReturn(count1);
        given(seatRepository.countSeatsByStatusAndShowIdAndZoneId(SeatStatus.AVAILABLE, showId, zoneId2)).willReturn(count2);
        given(seatRepository.countSeatsByStatusAndShowIdAndZoneId(SeatStatus.AVAILABLE, showId, zoneId3)).willReturn(count3);

        // when
        Map<GradeId, Long> countMap = target.getSeatsCountMapByGradeId(mockShow);

        // then
        verify(seatRepository, times(zoneList.size())).countSeatsByStatusAndShowIdAndZoneId(eq(SeatStatus.AVAILABLE), anyLong(), anyLong());
        long gradeSize = 2;
        assertThat(countMap.size()).isEqualTo(gradeSize);
        assertThat(countMap.get(new GradeId(gradeId1))).isEqualTo(count1);
        assertThat(countMap.get(new GradeId(gradeId2))).isEqualTo(count2 + count3);

    }
}
