package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.EventType;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.event.concert.ArtistResponse;
import org.flab.api.domain.event.dto.event.musical.CastResponse;
import org.flab.api.domain.event.dto.seat.RemainSeatResponse;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.domain.event.dto.show.ShowSimpleResponse;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.domain.event.service.SeatService;
import org.flab.api.domain.event.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events/{eventId}/shows")
public class ShowController {

    private final EventService eventService;
    private final ShowService showService;
    private final SeatService seatService;

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


        EventType eventType = eventService.getTypeById(eventId);
        List<CastResponse> castResponseList = null;
        List<ArtistResponse> artistResponseList = null;
        if(EventType.CONCERT.equals(eventType)) {
            artistResponseList = show.getShowArtistList().stream().map(ArtistResponse::new).toList();
        } else if (EventType.MUSICAL.equals(eventType)) {
            castResponseList = show.getShowCastList().stream().map(CastResponse::new).toList();;
        }

        ShowResponse response = new ShowResponse(show, remainSeatResponseList, castResponseList, artistResponseList);
        return ResponseEntity.ok(response);
    }
}
