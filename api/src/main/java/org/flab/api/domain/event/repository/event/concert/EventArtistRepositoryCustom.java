package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.event.concert.EventArtist;

import java.util.List;

public interface EventArtistRepositoryCustom {
    List<EventArtist> findEventArtistsByEventId(Long eventId);
}
