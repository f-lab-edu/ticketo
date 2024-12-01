package org.flab.api.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Show;
import org.flab.api.domain.event.repository.show.ShowRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.flab.api.global.exception.ValidateException;
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
            throw new ValidateException(ErrorCode.EVENT_HAS_NO_SHOW);
        }
        return showList;
    }

    public Show getShowByIdAndEventId(long showId, long eventId) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOW_NOT_FOUND));
        if(show.getEvent().getId() != eventId) {
            throw new ValidateException(ErrorCode.INVALID_EVENT_SHOW);
        }
        return show;
    }
}
