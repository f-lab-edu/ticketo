package org.flab.api.domain.event.application;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Cast;
import org.flab.api.domain.event.domain.Character;
import org.flab.api.domain.event.dto.event.response.CharacterResponse;
import org.flab.api.domain.event.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CastResponseService {

    private final CharacterRepository characterRepository;

    public List<CharacterResponse> getEventCastResponseListByCharacter(Long eventId) {
        List<Character> characterList = characterRepository.findByEventId(eventId);
        return characterList.stream()
                .map(character -> new CharacterResponse(
                        character.getId(),
                        character.getName(),
                        character.getCastList().stream()
                                .map(Cast::toResponse)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
