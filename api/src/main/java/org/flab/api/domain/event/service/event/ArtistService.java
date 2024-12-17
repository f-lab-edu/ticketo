package org.flab.api.domain.event.service.event;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.concert.Artist;
import org.flab.api.domain.event.domain.event.concert.EventArtist;
import org.flab.api.domain.event.domain.show.ShowArtist;
import org.flab.api.domain.event.repository.event.concert.EventArtistRepository;
import org.flab.api.domain.event.repository.event.concert.ShowArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArtistService {

    private final ShowArtistRepository showArtistRepository;
    private final EventArtistRepository eventArtistRepository;

    public List<Artist> getArtistListByEventId(Long eventId) {
        List<EventArtist> eventArtistList = eventArtistRepository.findEventArtistsByEventId(eventId);
        return eventArtistList.stream().map(EventArtist::getArtist).toList();
    }

    public List<Artist> getArtistListByShowId(Long showId) {
        List<ShowArtist> showArtistList = showArtistRepository.findShowArtistsByShowId(showId);
        return showArtistList.stream().map(showArtist -> showArtist.getEventArtist().getArtist()).toList();
    }
}
