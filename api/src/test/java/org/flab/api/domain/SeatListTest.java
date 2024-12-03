package org.flab.api.domain;

import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SeatListTest {

    @ParameterizedTest
    @MethodSource("provideSeatCountTestData")
    @DisplayName("좌석 등급 별 좌석 수 카운트")
    public void countSeatByGradeId(long gradeId, long expectedCount) {
        // given
        List<Seat> seatEntityList = createSeatListMockByGradeId(gradeId, expectedCount);

        // when
        SeatList target = new SeatList(seatEntityList);
        long count = target.countSeatByGradeId(gradeId);

        // then
        assertThat(count).isEqualTo(expectedCount);
    }

    private List<Seat> createSeatListMockByGradeId(long gradeId, long expectedCount) {
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < expectedCount; i++) {
            Grade gradeMock = mock(Grade.class);
            given(gradeMock.getId()).willReturn(gradeId);

            Seat seatMock = mock(Seat.class);
            given(seatMock.getGrade()).willReturn(gradeMock);

            seatList.add(seatMock);
        }
        return seatList;
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
