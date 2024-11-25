package org.flab.api.domain.event.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;

import java.util.Optional;

import static org.flab.api.domain.category.domain.QCategory.category;
import static org.flab.api.domain.event.domain.QEvent.event;
import static org.flab.api.domain.event.domain.musical.QCharacter.character;
import static org.flab.api.domain.event.domain.musical.QMusical.musical;

@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<Event> findEventWithRelationEntity(Long eventId) {
        JPAQuery<Event> query = queryFactory
            .select(event).distinct()
            .from(event)
            .leftJoin(event.category, category).fetchJoin()
            .leftJoin(category.parent).fetchJoin()
            .leftJoin(event.place).fetchJoin()
            .leftJoin(event.region).fetchJoin();

        if(isMusical()) {
            query
                .leftJoin(musical)
                .on(event.id.eq(musical.id))
                .leftJoin(musical.characterList, character).fetchJoin();
        }

        return Optional.ofNullable(
                query
                .where(event.id.eq(eventId))
                .fetchOne()
        );
    }

    private boolean isMusical() {
        return EventType.MUSICAL.toString().equals(event.type.toString());
    }
}
