package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.BulkInsertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatGenerateService {

    private final BulkInsertRepository bulkInsertRepository;
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

        seatList = generateSeatListByGrade(show.getEvent(), show);
        if(!seatList.isEmpty()) {
            bulkInsertRepository.saveAll(seatList);
            seatCacheService.evictPreparedSeatsForShow(show.getId());
        }
        return seatList;
    }

    /**
     * 좌석 목록 데이터 생성
     * @param event  공연
     * @param show 공연 회차
     * @return 좌석 목록
     */
    private List<Seat> generateSeatListByGrade(Event event, Show show) {
        if(seatCacheService.preparedSeatsForShow(show.getId())) {
            return new ArrayList<>();
        }
        List<Seat> seatList = new ArrayList<>();

        // 등급(구역)에 따라 좌석 생성
        for (Grade grade : event.getGradeList()) {
            Zone zone = zoneService.getZonesByPlaceIdAndGradeId(event.getPlace().getId(), grade.getId());

            for (long row = 0; row < zone.getRows(); row++) {
                for (long col = 0; col < zone.getCols(); col++) {
                    seatList.add(new Seat(show, zone, zone.getGrade(), row, col, SeatStatus.AVAILABLE, ZonedDateTime.now()));
                }
            }
        }
        return seatList;
    }
}
