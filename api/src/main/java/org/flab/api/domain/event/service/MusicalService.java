package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.repository.musical.MusicalRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MusicalService {

    private final MusicalRepository musicalRepository;

    public Musical getEvent(long eventId) {
       Optional<Musical> musical = musicalRepository.findMusicalWithRelationEntity(eventId);
       return musical.orElseThrow(() -> new CustomException(ErrorCode.EVENT_NOT_FOUND));
    }
}
