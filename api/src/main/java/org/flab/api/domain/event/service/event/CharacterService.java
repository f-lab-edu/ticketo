package org.flab.api.domain.event.service.event;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.repository.event.musical.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<Character> getCharacterListByEventId(Long eventId) {
        return characterRepository.findAllByEventId(eventId);
    }
}
