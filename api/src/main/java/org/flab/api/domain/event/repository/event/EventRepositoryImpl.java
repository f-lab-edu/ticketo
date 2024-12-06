package org.flab.api.domain.event.repository.event;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.event.EventType;

import java.util.Optional;

import static org.flab.api.domain.category.domain.QCategory.category;
import static org.flab.api.domain.event.domain.event.QEvent.event;

@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<EventType> findEventTypeById(long eventId) {
        return Optional.ofNullable(
                queryFactory
                        .select(event.type)
                        .from(event)
                        .where(event.id.eq(eventId))
                        .fetchOne());
    }

    public Optional<Event> findEventWithRelationEntity(long eventId) {
        return Optional.ofNullable(
                queryFactory
                        .select(event).distinct()
                        .from(event)
                        .leftJoin(event.category, category).fetchJoin()
                        .leftJoin(category.parent).fetchJoin()
                        .leftJoin(event.place).fetchJoin()
                        .leftJoin(event.region).fetchJoin()
                        .where(event.id.eq(eventId))
                        .fetchOne()
        );
    }
}
