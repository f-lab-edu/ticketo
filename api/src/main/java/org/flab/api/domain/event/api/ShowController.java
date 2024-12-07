package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.EventType;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.seat.RemainSeatResponse;
import org.flab.api.domain.event.dto.show.ParticipantResponse;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.domain.event.dto.show.ShowSimpleResponse;
import org.flab.api.domain.event.service.ArtistService;
import org.flab.api.domain.event.service.SeatService;
import org.flab.api.domain.event.service.ShowCastService;
import org.flab.api.domain.event.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events/{eventId}/shows")
public class ShowController {

    private final ShowService showService;
    private final SeatService seatService;
    private final ArtistService artistService;
    private final ShowCastService showCastService;

    @GetMapping
    public ResponseEntity<ShowListResponse> getShowListResponse(@PathVariable("eventId") long eventId) {
        List<Show> showList = showService.getShowListByEventId(eventId);
        List<ShowSimpleResponse> showSimpleResponseList = showList.stream().map(ShowSimpleResponse::new).toList();
        ShowListResponse response = new ShowListResponse(showSimpleResponseList.size(), eventId, showSimpleResponseList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ShowResponse> getShowResponse(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId) {
        Show show = showService.getShowByIdAndEventId(showId, eventId);
        SeatList seatList =  seatService.getSeatListByShowIdAndStatus(showId, SeatStatus.AVAILABLE);
        List<Grade> gradeList = show.getEvent().getGradeList();
        List<RemainSeatResponse> remainSeatResponseList = gradeList.stream()
                .map(grade ->
                    new RemainSeatResponse(grade, seatList.countSeatByGradeId(grade.getId())
                )).toList();

        ShowResponse response = getShowResponse(eventId, show, remainSeatResponseList);
        return ResponseEntity.ok(response);
    }

    private ShowResponse getShowResponse(Long eventId, Show show, List<RemainSeatResponse> remainSeatResponseList) {
        EventType eventType = show.getEvent().getType();
        return new ShowResponse(show, remainSeatResponseList, getPerformerResponseList(eventType, eventId, show.getId()));
    }

    private List<ParticipantResponse> getPerformerResponseList(EventType type, Long eventId, Long showId) {
        List<ParticipantResponse> participantResponseList = new ArrayList<>();
        if(EventType.CONCERT.equals(type)) {
           participantResponseList = artistService.getArtistListByEventId(eventId).stream().map(ParticipantResponse::new).toList();
        } else if(EventType.MUSICAL.equals(type)) {
           participantResponseList = showCastService.getShowCastListWithActorByShowId(showId).stream().map(ParticipantResponse::new).toList();
        }
        return participantResponseList;
    }
}
