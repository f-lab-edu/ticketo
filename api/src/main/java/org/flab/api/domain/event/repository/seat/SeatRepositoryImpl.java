package org.flab.api.domain.event.repository.seat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;

import java.util.List;

import static org.flab.api.domain.event.domain.seat.QGrade.grade;
import static org.flab.api.domain.event.domain.seat.QSeat.seat;

@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Seat> findSeatListByShowIdAndStatus(long showId, SeatStatus status) {
        return  queryFactory
                .select(seat)
                .from(seat)
                .leftJoin(seat.grade, grade)
                .where(seat.show.id.eq(showId).and(seat.status.eq(status)))
                .fetch();
    }
}
