package org.flab.api.domain.event.repository.event.concert;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.Concert;

import java.util.Optional;

import static org.flab.api.domain.category.domain.QCategory.category;
import static org.flab.api.domain.event.domain.event.concert.QConcert.concert;
import static org.flab.api.domain.event.domain.event.concert.QEventArtist.eventArtist;

@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<Concert> findConcertWithRelationEntity(long eventId) {
        return Optional.ofNullable(
                queryFactory
                        .select(concert).distinct()
                        .from(concert)
                        .leftJoin(concert.category, category).fetchJoin()
                        .leftJoin(category.parent).fetchJoin()
                        .leftJoin(concert.place).fetchJoin()
                        .leftJoin(concert.region).fetchJoin()
                        .leftJoin(concert.artistList, eventArtist).fetchJoin()
                        .leftJoin(eventArtist.artist).fetchJoin()
                        .where(concert.id.eq(eventId))
                        .fetchOne()
        );
    }
}
