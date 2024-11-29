package org.flab.api.domain.event.repository.musical;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.musical.Musical;

import java.util.Optional;

import static org.flab.api.domain.category.domain.QCategory.category;
import static org.flab.api.domain.event.domain.musical.QCharacter.character;
import static org.flab.api.domain.event.domain.musical.QMusical.musical;

@RequiredArgsConstructor
public class MusicalRepositoryImpl implements MusicalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<Musical> findMusicalWithRelationEntity(long eventId) {
        return Optional.ofNullable(
                queryFactory
                    .select(musical).distinct()
                    .from(musical)
                    .leftJoin(musical.category, category).fetchJoin()
                    .leftJoin(category.parent).fetchJoin()
                    .leftJoin(musical.place).fetchJoin()
                    .leftJoin(musical.region).fetchJoin()
                    .leftJoin(musical.characterList, character).fetchJoin()
                    .where(musical.id.eq(eventId))
                    .fetchOne()
        );
    }
}
