package org.flab.api.domain.event.repository.seat;

import org.flab.api.domain.event.domain.seat.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone getZonesByPlaceIdAndGradeId(long placeId, long gradeId);
}
