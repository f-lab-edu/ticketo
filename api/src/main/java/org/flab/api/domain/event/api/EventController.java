package org.flab.api.domain.event.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.event.concert.Artist;
import org.flab.api.domain.event.domain.event.concert.Concert;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.event.musical.Musical;
import org.flab.api.domain.event.domain.show.ShowCast;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.dto.event.EventListResponse;
import org.flab.api.domain.event.dto.event.EventRequestParams;
import org.flab.api.domain.event.dto.event.EventResponse;
import org.flab.api.domain.event.dto.event.MembershipRequest;
import org.flab.api.domain.event.dto.event.concert.ConcertResponse;
import org.flab.api.domain.event.dto.event.musical.MusicalResponse;
import org.flab.api.domain.event.dto.seat.DiscountPolicyResponse;
import org.flab.api.domain.event.dto.seat.EventPriceListResponse;
import org.flab.api.domain.event.dto.seat.SeatGradeResponse;
import org.flab.api.domain.event.service.ArtistService;
import org.flab.api.domain.event.service.CharacterService;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.domain.event.service.ShowCastService;
import org.flab.api.global.common.ListRequestParams;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.InvalidEventTypeException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final ArtistService artistService;
    private final CharacterService characterService;
    private final ShowCastService showCastService;

    @GetMapping
    public ResponseEntity<EventListResponse> getEventList(@ModelAttribute ListRequestParams requestParam, @ModelAttribute @Valid EventRequestParams eventRequestParams) {
        PageRequest.of(requestParam.getPage(), requestParam.getPageSize());
//        EventListResponse response = EventDummyGenerator.generateDummyEventListResponse();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable long eventId) {
        EventResponse response = getEventResponse(eventId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}/prices")
    public ResponseEntity<EventPriceListResponse> getEventPriceList(@PathVariable long eventId){
        EventPriceListResponse response = getEventPriceListResponse(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/pre-reservation/register")
    public ResponseEntity<Void> registerPreReservation(@RequestBody MembershipRequest requestBody, @PathVariable String eventId) {
        return ResponseEntity.ok().build();
    }

    private EventResponse getEventResponse(long eventId) {
        Event event =  eventService.getEvent(eventId);
        return switch (event.getType()) {
            case CONCERT -> convertToConcertResponse(event);
            case MUSICAL -> convertToMusicalResponse(event);
            default -> throw new InvalidEventTypeException(ErrorCode.INVALID_EVENT_TYPE);
        };
    }

    private EventPriceListResponse getEventPriceListResponse(long eventId) {
        Event event = eventService.getEvent(eventId);
        List<SeatGradeResponse> seatGradeResponses = getSeatGradeResponseList(event);
        return new EventPriceListResponse(seatGradeResponses.size(), seatGradeResponses);
    }

    private List<SeatGradeResponse> getSeatGradeResponseList(Event event) {
        List<Grade> gradeList = event.getGradeList();
        List<DiscountPolicy> discountPolicyList = event.getDiscountPolicyList();

        List<SeatGradeResponse> gradeResponseList = new ArrayList<>();
        gradeList.forEach(grade -> {
            List<DiscountPolicyResponse> discountPolicyResponseList = getDiscountPolicyResponseList(grade.getBasePrice(), discountPolicyList);
            SeatGradeResponse gradeResponse = new SeatGradeResponse(grade, discountPolicyResponseList);
            gradeResponseList.add(gradeResponse);

        });
        return gradeResponseList;
    }

    private List<DiscountPolicyResponse> getDiscountPolicyResponseList(long basePrice, List<DiscountPolicy> discountPolicyList) {
        return discountPolicyList.stream().map(discountPolicy -> new DiscountPolicyResponse(discountPolicy, basePrice)).toList();
    }

    private EventResponse convertToConcertResponse(Event event) {
        List<Artist> artistList = artistService.getArtistListByEventId(event.getId());
        return new ConcertResponse((Concert) event, artistList);
    }

    private EventResponse convertToMusicalResponse(Event event) {
        List<Character> characterList = characterService.getCharacterListByEventId(event.getId());
        List<ShowCast> castList = showCastService.getShowCastListWithActorByCharacterList(characterList);
        return new MusicalResponse((Musical) event, characterList, castList);

    }
}
