package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;
    private final ZoneService zoneService;

    /**
     * 예매 가능한 좌석 수 조회
     * @param showId 공연 회차 아이디
     * @param zoneId 공연장 구역 아이디
     * @return 예매 가능한 좌석 수
     */
    public long countAvailableSeatsByZone(long showId, long zoneId) {
        return seatRepository.countSeatsByStatusAndShowIdAndZoneId(SeatStatus.AVAILABLE, showId, zoneId);
    }

    /**
     * 특정 회차 내 등급 별 예매 가능한 좌석 수 조회
     * @param show 회차
     * @return Map<GradeId,Long> : 좌석 등급 아이디, 좌석 수
     */
    public Map<GradeId,Long> getSeatsCountMapByGradeId(Show show) {
        List<Zone> zoneList = zoneService.getZoneList(show.getEvent().getPlace().getId());
        Map<GradeId, Long> map = new HashMap<>();
        for(Zone zone : zoneList) {
            GradeId gradeId = new GradeId(zone.getGrade().getId());
            long count = countAvailableSeatsByZone(show.getId(), zone.getId());
            if(map.containsKey(gradeId)) {
                map.put(gradeId, map.get(gradeId) + count);
            } else {
                map.put(gradeId, count);
            }
        }
        return map;
    }
}
