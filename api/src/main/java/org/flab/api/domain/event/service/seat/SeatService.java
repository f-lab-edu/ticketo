package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.flab.api.domain.place.domain.Place;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatCacheService seatCacheService;
    private final ZoneService zoneService;

    /**
     * 회차 별 좌석 목록 데이터 생성
     * @param show 공연 회차
     */
    @Transactional
    public List<Seat> generateSeatsForShow(Show show) {
        List<Seat> seatList = new ArrayList<>();
        if(seatCacheService.preparedSeatsForShow(show.getId())) {
           return seatList;
        };

        seatList = generateSeatListByPlace(show.getEvent().getPlace(), show);
        if(!seatList.isEmpty()) {
            List<Seat> createdSeatList = seatRepository.saveAll(seatList);
            seatCacheService.evictPreparedSeatsForShow(show.getId());
            createdSeatList.forEach(seatCacheService::cacheSeat);
        }
        return seatList;
    }

    /**
     * 공연장 별 좌석 목록 데이터 생성
     * @param place  공연장
     * @param show 공연 회차
     * @return 좌석 목록
     */
    private List<Seat> generateSeatListByPlace(Place place, Show show) {
        if(seatCacheService.preparedSeatsForShow(show.getId())) {
            return new ArrayList<>();
        };
        return place.getZoneList().stream().flatMap(zone -> generateSeatListForZone(zone, show).stream()).toList();
    }

    private List<Seat> generateSeatListForZone(Zone zone, Show show) {
        if(seatCacheService.preparedSeatsForShow(show.getId())) {
            return new ArrayList<>();
        }
        List<Seat> seatList = new ArrayList<>();
        for(long row = 0; row < zone.getRows(); row++) {
            for(long col = 0; col < zone.getCols(); col++) {
                seatList.add(new Seat(show, zone, row, col, SeatStatus.AVAILABLE, ZonedDateTime.now()));
            }
        }
        return seatList;
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
}
