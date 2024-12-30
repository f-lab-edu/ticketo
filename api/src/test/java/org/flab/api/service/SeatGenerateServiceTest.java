package org.flab.api.service;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.BulkInsertRepository;
import org.flab.api.domain.event.service.seat.SeatCacheService;
import org.flab.api.domain.event.service.seat.SeatGenerateService;
import org.flab.api.domain.event.service.seat.ZoneService;
import org.flab.api.domain.place.domain.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SeatGenerateServiceTest {

    @Mock
    private BulkInsertRepository bulkInsertRepository;

    @Mock
    private SeatCacheService seatCacheService;

    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private SeatGenerateService target;

    @Test
    @DisplayName("회차 별 좌석 생성")
    public void generateSeatsForShow() {
        // given
        long showId = 1L;
        long placeId = 1L;
        long gradeId1 = 1L, gradeId2 = 2L;
        long zone1Rows = 10L;
        long zone2Rows = 15L;
        long zone1Cols = 20L;
        long zone2Cols = 20L;

        Grade mockGrade1 = mock(Grade.class);
        Grade mockGrade2 = mock(Grade.class);
        given(mockGrade1.getId()).willReturn(gradeId1);
        given(mockGrade2.getId()).willReturn(gradeId2);

        Zone mockZone1 = mock(Zone.class);
        Zone mockZone2 = mock(Zone.class);
        given(mockZone1.getRows()).willReturn(zone1Rows);
        given(mockZone1.getCols()).willReturn(zone1Cols);
        given(mockZone1.getGrade()).willReturn(mockGrade1);
        given(mockZone2.getRows()).willReturn(zone2Rows);
        given(mockZone2.getCols()).willReturn(zone2Cols);
        given(mockZone2.getGrade()).willReturn(mockGrade2);

        Place mockPlace = mock(Place.class);
        given(mockPlace.getId()).willReturn(placeId);
        Event mockEvent = mock(Event.class);
        given(mockEvent.getPlace()).willReturn(mockPlace);
        given(mockEvent.getGradeList()).willReturn(List.of(mockGrade1, mockGrade2));
        Show mockShow = mock(Show.class);
        given(mockShow.getId()).willReturn(showId);
        given(mockShow.getEvent()).willReturn(mockEvent);

        given(zoneService.getZonesByPlaceIdAndGradeId(placeId, gradeId1)).willReturn(mockZone1);
        given(zoneService.getZonesByPlaceIdAndGradeId(placeId, gradeId2)).willReturn(mockZone2);
        given(seatCacheService.preparedSeatsForShow(showId)).willReturn(false);

        // when
        List<Seat> seatList = target.generateSeatsForShow(mockShow);

        // then
        long totalSeatCount = zone1Rows * zone1Cols + zone2Rows * zone2Cols;
        assertThat(seatList.size()).isEqualTo(totalSeatCount);
    }
}
