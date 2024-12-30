package org.flab.api.service;

import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.flab.api.domain.event.service.seat.SeatViewService;
import org.flab.api.domain.event.service.seat.ZoneService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SeatViewServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private SeatViewService target;


    @Test
    @DisplayName("등급 별 좌석 수를 담은 Map 반환")
    public void getSeatsCountMapTest() {
        // given
        long showId = 1L;
        long gradeId1 = 1L, gradeId2 = 2L;
        List<Long> gradeIdList = List.of(gradeId1, gradeId2);

        Map<GradeId, Long> resultMap = Map.of(new GradeId(gradeId1), 10L, new GradeId(gradeId2), 20L);
        given(seatRepository.countSeatsByStatusAndShowIdAndGradeIdList(SeatStatus.AVAILABLE, showId, gradeIdList)).willReturn(resultMap);

        Show mockShow = mock(Show.class);
        given(mockShow.getId()).willReturn(showId);

        // when
        Map<GradeId, Long> countMap = target.getSeatsCountMapByGradeId(gradeIdList, mockShow);

        // then
        verify(seatRepository, times(1)).countSeatsByStatusAndShowIdAndGradeIdList(eq(SeatStatus.AVAILABLE), eq(showId), eq(gradeIdList));
        assertThat(countMap).isEqualTo(resultMap);
    }
}
