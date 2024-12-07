package org.flab.api.domain.event.repository.event.musical;

import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.show.ShowCast;

import java.util.List;

public interface ShowCastRepositoryCustom {
    List<ShowCast> findShowCastListWithActorByCharacterList(List<Character> characterList);
    List<ShowCast> findShowCastListWithActorByShowId(Long showId);
}
