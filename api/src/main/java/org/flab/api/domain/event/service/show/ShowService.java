package org.flab.api.domain.event.service.show;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.show.ShowRepository;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.flab.api.global.exception.InvalidShowException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ShowService {

    private final ShowRepository showRepository;

    public List<Show> getShowListByEventId(long eventId) {
        List<Show> showList = showRepository.findAllByEventId(eventId);
        if(showList.isEmpty()) {
            throw new InvalidShowException(ErrorCode.EVENT_HAS_NO_SHOW);
        }
        return showList;
    }

    public Show getShow(long eventId, long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOW_NOT_FOUND));
        if(show.getEvent().getId() != eventId) {
            throw new InvalidShowException(ErrorCode.INVALID_EVENT_SHOW);
        }
        return show;
    }
}
