package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> getZonesByPlaceId(long placeId);
}
