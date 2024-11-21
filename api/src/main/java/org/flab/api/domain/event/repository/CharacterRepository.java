package org.flab.api.domain.event.repository;

import org.flab.api.domain.event.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByEventId(Long id);
}
