package org.flab.api.domain.event.repository.show;

import org.flab.api.domain.event.domain.Show;

import java.util.List;

public interface ShowRepositoryCustom {

    List<Show> findShowsByEventId(long eventId);
}
