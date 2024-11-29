package org.flab.api.domain.event.repository.show;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Show;

import java.util.List;

import static org.flab.api.domain.event.domain.QShow.show;

@RequiredArgsConstructor
public class ShowRepositoryImpl implements ShowRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Show> findShowsByEventId(long eventId) {
        return queryFactory
                .selectFrom(show)
                .where(show.event.id.eq(eventId))
                .fetch();
    }
}
