package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.event.concert.EventArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventArtistRepository extends JpaRepository<EventArtist, Long>, EventArtistRepositoryCustom {

}
