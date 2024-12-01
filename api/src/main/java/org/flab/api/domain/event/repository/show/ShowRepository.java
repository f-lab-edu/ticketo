package org.flab.api.domain.event.repository.show;

import org.flab.api.domain.event.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long>, ShowRepositoryCustom {
    List<Show> findAllByEventId(long eventId);
}
