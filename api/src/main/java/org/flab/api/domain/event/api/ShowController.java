package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Show;
import org.flab.api.domain.event.domain.musical.Cast;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.SeatList;
import org.flab.api.domain.event.domain.seat.SeatStatus;
import org.flab.api.domain.event.dto.event.response.musical.CastResponse;
import org.flab.api.domain.event.dto.seat.response.RemainSeatResponse;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowResponse;
import org.flab.api.domain.event.dto.show.ShowSimpleResponse;
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

    private final SeatService seatService;
    private final ShowService showService;

    @GetMapping
    public ResponseEntity<ShowListResponse> getShowListResponse(@PathVariable("eventId") long eventId) {
        List<Show> showList = showService.getShowListByEventId(eventId);
        List<ShowSimpleResponse> showSimpleResponseList = showList.stream().map(Show::toSimpleResponse).toList();
        ShowListResponse response = new ShowListResponse(showSimpleResponseList.size(), eventId, showSimpleResponseList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ShowResponse> getShowResponse(@PathVariable("eventId") long eventId, @PathVariable("showId") long showId) {
        Show show = showService.getShowByIdAndEventId(showId, eventId);
        SeatList seatList =  seatService.getSeatListByShowIdAndStatus(showId, SeatStatus.AVAILABLE);

        List<Grade> gradeList = show.getEvent().getGradeList();
        List<Cast> castList = show.getCastList();

        List<RemainSeatResponse> remainSeatResponseList = gradeList.stream()
                .map(grade ->
                    new RemainSeatResponse(grade, seatList.countSeatByGradeId(grade.getId())
                )).toList();

        List<CastResponse> castResponseList = castList.stream().map(Cast::toResponse).toList();

        ShowResponse response = new ShowResponse(show, remainSeatResponseList, castResponseList);
        return ResponseEntity.ok(response);
    }
}
