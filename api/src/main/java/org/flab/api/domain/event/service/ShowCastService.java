package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.show.ShowCast;
import org.flab.api.domain.event.repository.event.musical.ShowCastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ShowCastService {

    private final ShowCastRepository showCastRepository;

    public List<ShowCast> getShowCastListWithActorByShowId(Long showId) {
        return showCastRepository.findShowCastListWithActorByShowId(showId);
    }

    public List<ShowCast> getShowCastListWithActorByCharacterList(List<Character> characterList) {
      return showCastRepository.findShowCastListWithActorByCharacterList(characterList);
    }
}
