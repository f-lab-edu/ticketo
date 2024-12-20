package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.seat.SeatListResponse;
import org.flab.api.domain.event.dto.seat.SeatResponse;
import org.flab.api.domain.event.dto.seat.ZoneResponse;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.flab.api.domain.place.domain.Place;
import org.flab.api.global.cache.CacheConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatViewService {

    private final SeatRepository seatRepository;
    private final ZoneService zoneService;

    /**
     * 회차 구역 별 좌석 목록 조회
     */
    public List<Seat> getSeatListByZone(long showId, long zoneId) {
        return seatRepository.findSeatsByShowIdAndZoneId(showId, zoneId);
    }


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


    @Cacheable(value = CacheConstant.SEAT, key = "#seatId")
    public SeatResponse getSeat(long seatId) {
        return new SeatResponse(seatRepository.findSeatById(seatId));
    }

    @Cacheable(value = CacheConstant.SEAT, key="T(org.flab.api.global.cache.CacheKeyGenerator).getZoneSeatListKeyGenerate(#show.id, #zone.id)")
    public SeatListResponse getSeatListResponse(Show show, Zone zone) {
        Event event = show.getEvent();
        Place place = event.getPlace();
        List<Seat> seatList = getSeatListByZone(show.getId(), zone.getId());
        return new SeatListResponse(place.getId(), new ZoneResponse(zone, seatList));
    }
}
