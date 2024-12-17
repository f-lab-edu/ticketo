package org.flab.api.domain.event.repository.event.concert;

import org.flab.api.domain.event.domain.show.ShowArtist;

import java.util.List;

public interface ShowArtistRepositoryCustom {
    List<ShowArtist> findShowArtistsByShowId(Long showId);
}
