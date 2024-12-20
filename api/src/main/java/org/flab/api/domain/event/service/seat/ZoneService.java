package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.repository.seat.ZoneRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public Zone getZone(long zoneId) {
        return zoneRepository.findById(zoneId).orElseThrow(() -> new NotFoundException(ErrorCode.ZONE_NOT_FOUND));
    }
    /**
     * 공연장 별 구역 조회
     * @param placeId 공연장 아이디
     * @return List 좌석 목록
     */
    public List<Zone> getZoneList(long placeId) {
        return zoneRepository.getZonesByPlaceId(placeId);
    }
}
