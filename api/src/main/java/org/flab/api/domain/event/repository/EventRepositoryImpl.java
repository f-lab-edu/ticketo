package org.flab.api.domain.event.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;

import java.util.Optional;

import static org.flab.api.domain.category.domain.QCategory.category;
import static org.flab.api.domain.event.domain.QCharacter.character;
import static org.flab.api.domain.event.domain.QEvent.event;

@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<Event> findEventWithRelationEntity(Long eventId) {
        return Optional.ofNullable(
                queryFactory.select(event)
                        .distinct()
                        .from(event)
                        .leftJoin(event.category, category).fetchJoin()
                        .leftJoin(category.parent).fetchJoin()
                        .leftJoin(event.place).fetchJoin()
                        .leftJoin(event.region).fetchJoin()
                        .leftJoin(event.characterList, character).fetchJoin()
                        .where(event.id.eq(eventId))
                        .fetchOne()
        );
    }
}
