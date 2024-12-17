package org.flab.api.domain.event.repository.event.concert;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.show.ShowArtist;

import java.util.List;

import static org.flab.api.domain.event.domain.event.concert.QArtist.artist;
import static org.flab.api.domain.event.domain.event.concert.QEventArtist.eventArtist;
import static org.flab.api.domain.event.domain.show.QShowArtist.showArtist;

@RequiredArgsConstructor
public class ShowArtistRepositoryImpl implements ShowArtistRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ShowArtist> findShowArtistsByShowId(Long showId) {
        return queryFactory
                .select(showArtist)
                .from(showArtist)
                .leftJoin(showArtist.eventArtist, eventArtist).fetchJoin()
                .leftJoin(eventArtist.artist, artist).fetchJoin()
                .where(showArtist.show.id.eq(showId))
                .fetch();
    }
}
