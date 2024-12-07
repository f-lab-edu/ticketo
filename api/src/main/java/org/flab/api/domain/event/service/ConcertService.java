package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.repository.concert.ConcertRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ConcertService {

    private final ConcertRepository concertRepository;

    public Concert getEvent(long eventId) {
        Optional<Concert> concert = concertRepository.findConcertWithRelationEntity(eventId);
        return concert.orElseThrow(() -> new CustomException(ErrorCode.EVENT_NOT_FOUND));
    }
}
