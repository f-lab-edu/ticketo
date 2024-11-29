package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Show;
import org.flab.api.domain.event.repository.show.ShowRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ShowService {

    private final ShowRepository showRepository;

    public List<Show> getShowListByEventId(long eventId) {
        List<Show> showList = showRepository.findShowsByEventId(eventId);
        if(showList.isEmpty()) {
            throw new CustomException(ErrorCode.EVENT_HAS_NO_SHOW);
        }
        return showList;
    }
}
