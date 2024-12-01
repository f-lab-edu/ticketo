package org.flab.api.service;

import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.service.SeatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {

    @InjectMocks
    private SeatService target;

    @ParameterizedTest
    @MethodSource("provideSeatCountTestData")
    @DisplayName("좌석 등급 별 좌석 수 카운트")
    public void countSeatByGradeId(long gradeId, long expectedCount) {
        // given
        List<Seat> seatList = createSeatListMockByGradeId(gradeId, expectedCount);

        // when
        long count = target.countSeatByGradeId(seatList, gradeId);

        // then
        assertThat(count).isEqualTo(expectedCount);
    }

    private List<Seat> createSeatListMockByGradeId(long gradeId, long expectedCount) {
        List<Seat> seatList = new java.util.ArrayList<>(createBaseSeatListMock());
        for(int i=0; i<expectedCount; i++) {
            Grade gradeMock = mock(Grade.class);
            when(gradeMock.getId()).thenReturn(gradeId);
            Seat seatMock = mock(Seat.class);
            when(seatMock.getGrade()).thenReturn(gradeMock);
            seatList.add(seatMock);
        }
        return seatList;
    }

    private List<Seat> createBaseSeatListMock() {
        Grade gradeMock1 = mock(Grade.class);
        Grade gradeMock2 = mock(Grade.class);
        Grade gradeMock3 = mock(Grade.class);
        when(gradeMock1.getId()).thenReturn(4L);
        when(gradeMock2.getId()).thenReturn(5L);
        when(gradeMock3.getId()).thenReturn(6L);

        Seat seatMock1 = mock(Seat.class);
        Seat seatMock2 = mock(Seat.class);
        Seat seatMock3 = mock(Seat.class);
        when(seatMock1.getGrade()).thenReturn(gradeMock1);
        when(seatMock2.getGrade()).thenReturn(gradeMock2);
        when(seatMock3.getGrade()).thenReturn(gradeMock3);

        return List.of(seatMock1, seatMock2, seatMock3);
    }

    private static Stream<Arguments> provideSeatCountTestData() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 4),
                Arguments.of(7, 0)
        );
    }
}
