package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.show.ShowArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowArtistRepository extends JpaRepository<ShowArtist, Long>, ShowArtistRepositoryCustom {
}
