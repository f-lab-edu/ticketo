package org.flab.api.domain.event.repository.event.musical;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.show.ShowCast;

import java.util.List;

import static org.flab.api.domain.event.domain.event.musical.QActor.actor;
import static org.flab.api.domain.event.domain.show.QShowCast.showCast;

@RequiredArgsConstructor
public class ShowCastRepositoryImpl implements ShowCastRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ShowCast> findShowCastListWithActorByCharacterList(List<Character> characterList) {
        return queryFactory
                .select(showCast)
                .from(showCast)
                .leftJoin(showCast.actor, actor).fetchJoin()
                .where(showCast.character.in(characterList))
                .fetch();
    }

    @Override
    public List<ShowCast> findShowCastListWithActorByShowId(Long showId) {
        return queryFactory
                .select(showCast)
                .from(showCast)
                .leftJoin(showCast.actor, actor).fetchJoin()
                .where(showCast.show.id.eq(showId))
                .fetch();
    }
}
