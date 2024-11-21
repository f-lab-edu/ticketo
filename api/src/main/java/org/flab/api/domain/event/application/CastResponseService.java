package org.flab.api.domain.event.application;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Cast;
import org.flab.api.domain.event.domain.Character;
import org.flab.api.domain.event.dto.event.response.EventCastResponse;
import org.flab.api.domain.event.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CastResponseService {

    private final CharacterRepository characterRepository;

    public List<EventCastResponse> getEventCastResponseList(Long eventId) {
        List<Character> characterList = characterRepository.findByEventId(eventId);
        List<EventCastResponse> eventCastResponseList = new ArrayList<>();

        for(Character character : characterList) {
            List<Cast> castList = character.getCastList();
            for(Cast cast : castList) {
                eventCastResponseList.add(new EventCastResponse(cast.getId(), cast.getName(), cast.getImage(), character.getId(), character.getName()));
            }
        }
        return eventCastResponseList;
    }
}
