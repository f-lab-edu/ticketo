package org.flab.api.domain.event.service.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.dto.seat.SeatResponse;
import org.flab.api.domain.event.repository.seat.SeatRepository;
import org.flab.api.global.cache.CacheConstant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SeatCacheService {

    private final SeatRepository seatRepository;

    @Cacheable(value = CacheConstant.SHOW, key="T(org.flab.api.global.cache.CacheKeyGenerator).preparedSeatsForShowKeyGenerate(#showId)")
    public boolean preparedSeatsForShow(Long showId) {
        return seatRepository.existsByShowId(showId);
    }

    @CacheEvict(value = CacheConstant.SHOW, key="T(org.flab.api.global.cache.CacheKeyGenerator).preparedSeatsForShowKeyGenerate(#showId)")
    public void evictPreparedSeatsForShow(Long showId) {

    }

    @Cacheable(value = CacheConstant.SEAT, key = "#seatId")
    public SeatResponse getSeat(long seatId) {
        return new SeatResponse(seatRepository.findSeatById(seatId));
    }
}
