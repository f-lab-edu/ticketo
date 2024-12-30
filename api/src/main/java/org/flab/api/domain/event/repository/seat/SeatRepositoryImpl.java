package org.flab.api.domain.event.repository.seat;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.SeatStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.flab.api.domain.event.domain.seat.QSeat.seat;

@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Map<GradeId, Long> countSeatsByStatusAndShowIdAndGradeIdList(SeatStatus seatStatus, long showId, List<Long> gradeIdList) {
        List<Tuple> results = queryFactory
                .select(seat.grade.id, seat.count())
                .from(seat)
                .where(
                        seat.show.id.eq(showId),
                        seat.grade.id.in(gradeIdList),
                        seat.status.eq(seatStatus)
                )
                .groupBy(seat.grade.id)
                .fetch();

        Map<GradeId, Long> gradeSeatCountMap = new HashMap<>();
        for (Tuple tuple : results) {
            GradeId gradeId = new GradeId(tuple.get(seat.grade.id));
            Long count = Optional.ofNullable(tuple.get(seat.count())).orElse(0L);
            gradeSeatCountMap.put(gradeId, count);
        }

        return gradeSeatCountMap;
    }
}
