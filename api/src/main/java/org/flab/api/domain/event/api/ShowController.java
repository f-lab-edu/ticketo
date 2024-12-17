package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.EventType;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.GradeId;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.dto.seat.RemainSeatResponse;
import org.flab.api.domain.event.dto.show.ParticipantResponse;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.domain.event.dto.show.ShowSimpleResponse;
import org.flab.api.domain.event.service.event.ArtistService;
import org.flab.api.domain.event.service.show.ShowCastService;
import org.flab.api.domain.event.service.show.ShowService;
import org.flab.api.domain.event.service.seat.GradeService;
import org.flab.api.domain.event.service.seat.SeatService;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.InvalidEventTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events/{eventId}/shows")
public class ShowController {

    private final ShowService showService;
    private final ArtistService artistService;
    private final ShowCastService showCastService;
    private final SeatService seatService;
    private final GradeService gradeService;


    @GetMapping
    public ResponseEntity<ShowListResponse> getShowListResponse(@PathVariable("eventId") long eventId) {
        List<Show> showList = showService.getShowListByEventId(eventId);
        List<ShowSimpleResponse> showSimpleResponseList = showList.stream().map(ShowSimpleResponse::new).toList();
        ShowListResponse response = new ShowListResponse(showSimpleResponseList.size(), eventId, showSimpleResponseList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ShowResponse> getShowResponse(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId) {
        Show show = showService.getShow(showId, eventId);
        ShowResponse response = getShowResponse(eventId, show);
        return ResponseEntity.ok(response);
    }

    private ShowResponse getShowResponse(Long eventId, Show show) {
        EventType eventType = show.getEvent().getType();
        List<Grade> gradeList = gradeService.getGradeList(eventId);

        Map<GradeId, Long> seatCountMap = seatService.getSeatsCountMapByGradeId(show);
        List<RemainSeatResponse> remainSeatResponseList = gradeList.stream()
                .map(grade ->
                        new RemainSeatResponse(grade, seatCountMap.getOrDefault(new GradeId(grade.getId()), 0L)
                        )).toList();
        return new ShowResponse(show, remainSeatResponseList, getPerformerResponseList(eventType, eventId, show.getId()));
    }

    private List<ParticipantResponse> getPerformerResponseList(EventType type, Long eventId, Long showId) {
        return switch (type) {
            case EventType.CONCERT ->
                    artistService.getArtistListByEventId(eventId).stream().map(ParticipantResponse::new).toList();
            case EventType.MUSICAL ->
                    showCastService.getShowCastListWithActorByShowId(showId).stream().map(ParticipantResponse::new).toList();
            default -> throw new InvalidEventTypeException(ErrorCode.INVALID_EVENT_TYPE);
        };
    }
}
