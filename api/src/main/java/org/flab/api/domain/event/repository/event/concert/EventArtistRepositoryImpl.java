package org.flab.api.domain.event.repository.event.concert;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.EventArtist;

import java.util.List;

import static org.flab.api.domain.event.domain.event.concert.QArtist.artist;
import static org.flab.api.domain.event.domain.event.concert.QEventArtist.eventArtist;

@RequiredArgsConstructor
public class EventArtistRepositoryImpl implements EventArtistRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<EventArtist> findEventArtistsByEventId(Long eventId) {
        return queryFactory
                .select(eventArtist)
                .from(eventArtist)
                .leftJoin(eventArtist.artist, artist).fetchJoin()
                .where(eventArtist.id.eq(eventId))
                .fetch();
    }
}
