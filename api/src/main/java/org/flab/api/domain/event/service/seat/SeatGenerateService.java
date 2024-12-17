package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.place.domain.Place;
import org.flab.api.domain.event.domain.seat.Seat;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.seat.Zone;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SeatGenerateService {

    private final SeatRepository seatRepository;

    /**
     * // 공연장, 공연장 구역 목록, 구역 별 좌석 목록 : 미리 생성하고 캐싱해둔다.(거의 변하지 않는 값)
     * // 회차별 좌석 목록 : 사용자가 특정 회차/특정 구역을 조회하면 구역별로 생성하고 캐싱.(좌석 상태도 같이 캐싱, 구역 별 좌석 목록을 기반으로 생성)
     * // 좌석 상태 : (회차 id, 회차 별 좌석 id, 상태 저장, 캐싱)
     * // 그러면 화면에서 사용자가 선택할 때 보는 좌석 목록은 어떻게 보여주지? - 회차별 좌석 목록 조회. 반영은 새로고침, 클릭 시 체크
     * // 좌석 선택 후 예매 진행 시 : 선택 시 좌석 개별 조회(상태 확인), 상태 업데이트(HOLD), 캐싱 업데이트
     * // 좌석 결제 시 : 좌석 개별 조회, 상태 업데이트(RESERVED), 캐싱 업데이트
     */
    public void tmp() {

    }


    /**
     * 회차 별 좌석 목록 데이터 생성
     * @param show 공연 회차
     */
    public List<Seat> generateSeatsForShow(Show show) {
        return seatRepository.saveAll(generateSeatListByPlace(show.getEvent().getPlace(), show));
    }

    /**
     * 공연장 별 좌석 목록 데이터 생성
     * @param place  공연장
     * @param show 공연 회차
     * @return 좌석 목록
     */
    private List<Seat> generateSeatListByPlace(Place place, Show show) {
        return place.getZoneList().stream().flatMap(zone -> generateSeatListForZone(zone, show).stream()).toList();
    }

    private List<Seat> generateSeatListForZone(Zone zone, Show show) {
        List<Seat> seatList = new ArrayList<>();
        for(long row = 0; row < zone.getRows(); row++) {
            for(long col = 0; col < zone.getCols(); col++) {
                seatList.add(generateSeat(show, zone, row, col));
            }
        }
        return seatList;
    }

    @Cacheable()
    public Seat generateSeat( Show show, Zone zone,long row, long col) {
        return new Seat(show, zone, row, col, SeatStatus.AVAILABLE);
    }
}
