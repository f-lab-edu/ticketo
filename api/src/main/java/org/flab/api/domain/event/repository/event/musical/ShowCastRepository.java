package org.flab.api.domain.event.repository.event.musical;

import org.flab.api.domain.event.domain.event.musical.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowCastRepository extends JpaRepository<Character, Long>, ShowCastRepositoryCustom {

}
