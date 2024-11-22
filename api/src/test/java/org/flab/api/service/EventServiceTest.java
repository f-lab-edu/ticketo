package org.flab.api.service;

import org.flab.api.BaseUnitTest;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.Image;
import org.flab.api.domain.event.domain.Period;
import org.flab.api.domain.event.domain.Place;
import org.flab.api.domain.event.domain.Region;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

public class EventServiceTest extends BaseUnitTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService sut;

    private Event event;
    private long eventId;

    @BeforeEach
    public void setupEvent() {
        eventId = 1L;
        event = new Event(eventId, "테스트 이벤트 이름", EventType.MUSICAL, 20, 140
                , "테스트 이벤트 설명", true
                , new Period(ZonedDateTime.now(), ZonedDateTime.now())
                , new Period(ZonedDateTime.now(), ZonedDateTime.now())
                , new Period(ZonedDateTime.now(), ZonedDateTime.now())
                , new Category(9L, "카테고리", new Category(1L, "최상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()), ZonedDateTime.now(), ZonedDateTime.now())
                , new Place(1L, "place", ZonedDateTime.now(), ZonedDateTime.now())
                , new Region(1L, "region", ZonedDateTime.now(), ZonedDateTime.now())
                , new Image("thumbmailImg", "posterImage")
                , List.of()
                , ZonedDateTime.now()
                , ZonedDateTime.now());
    }

    @Test
    @DisplayName("이벤트 조회")
    public void getEventTest() {
        // given
        given(eventRepository.findEventWithRelationEntity(eventId)).willReturn(Optional.of(event));

        // when
        Event result = sut.getEvent(eventId);

        // then
        assertThat(result.getId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("존재하지 않는 이벤트 조회")
    public void getEventNotFoundTest() {
        // given
        given(eventRepository.findEventWithRelationEntity(2L)).willReturn(Optional.empty());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> sut.getEvent(2L));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
