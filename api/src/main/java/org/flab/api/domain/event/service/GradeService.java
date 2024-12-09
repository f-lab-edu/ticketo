package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.repository.seat.GradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GradeService {

    private final GradeRepository gradeRepository;

    public List<Grade> getGradeList(Long eventId) {
        return gradeRepository.findByEventId(eventId);
    }
}
