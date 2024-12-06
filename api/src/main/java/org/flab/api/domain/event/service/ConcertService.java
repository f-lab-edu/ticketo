package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.Concert;
import org.flab.api.domain.event.repository.event.concert.ConcertRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ConcertService {

    private final ConcertRepository concertRepository;

    public Concert getConcert(long eventId) {
        Optional<Concert> concert = concertRepository.findConcertWithRelationEntity(eventId);
        return concert.orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }
}
