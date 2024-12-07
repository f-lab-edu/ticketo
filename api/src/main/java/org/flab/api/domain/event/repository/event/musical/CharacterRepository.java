package org.flab.api.domain.event.repository.event.musical;

import org.flab.api.domain.event.domain.event.musical.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByEventId(Long eventId);
}
