package org.flab.api.domain.event.application;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.dto.event.response.EventCastResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class EventResponseService {

    private final EventRepository eventRepository;
    private final CastResponseService castResponseService;

    public EventResponse getEventResponse(Long eventId) {
        System.out.println("eventResponseService");
        List<Event> eventList = eventRepository.findAll();
        System.out.println("eventList.size :" + eventList.size() );
        Optional<Event> event = eventRepository.findEventWithCategoryAndParentById(eventId);
        if(event.isEmpty()) {
            throw new CustomException(ErrorCode.EVENT_NOT_FOUND);
        }
        List<EventCastResponse> castResponseList = castResponseService.getEventCastResponseList(eventId);
        return event.get().toResponse(castResponseList);
    }

}
