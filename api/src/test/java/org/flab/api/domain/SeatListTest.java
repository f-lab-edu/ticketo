package org.flab.api.domain;

import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SeatListTest {

    @Test
    @DisplayName("좌석 등급 별 좌석 수 카운트")
    public void countSeatByGradeId() {
        // given
        Map<Long, Long> createSeatMap = new HashMap<>();
        createSeatMap.put(1L, 1L);
        createSeatMap.put(2L, 3L);
        createSeatMap.put(3L, 4L);
        createSeatMap.put(4L, 0L);

        List<Seat> seatEntityList = new ArrayList<>();
        for(Map.Entry<Long, Long> entry : createSeatMap.entrySet()) {
            seatEntityList.addAll(createSeatListMockByGradeId(entry.getKey(), entry.getValue()));
        }
        SeatList target = new SeatList(seatEntityList);

        for(Map.Entry<Long, Long> entry : createSeatMap.entrySet()) {
            // when
            long count = target.countSeatByGradeId(entry.getKey());
            // then
            assertThat(count).isEqualTo(entry.getValue());
        }
    }

    private List<Seat> createSeatListMockByGradeId(long gradeId, long createdCount) {
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < createdCount; i++) {
            Grade gradeMock = mock(Grade.class);
            given(gradeMock.getId()).willReturn(gradeId);

            Seat seatMock = mock(Seat.class);
            given(seatMock.getGrade()).willReturn(gradeMock);

            seatList.add(seatMock);
        }
        return seatList;
    }
}
