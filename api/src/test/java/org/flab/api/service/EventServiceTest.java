package org.flab.api.service;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.repository.event.EventRepository;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService target;

    @Test
    @DisplayName("이벤트 조회")
    public void getEvent() {
        // given
        long eventId = 1L;
        Event mockEvent = mock(Event.class);
        given(mockEvent.getId()).willReturn(eventId);
        given(eventRepository.findById(eventId)).willReturn(Optional.of(mockEvent));

        // when
        Event event = target.getEvent(eventId);

        // then
        verify(eventRepository).findById(eventId);
        assertThat(event.getId()).isEqualTo(eventId);
    }
    // 이벤트 조회 - 존재하지 않는 이벤트
    @Test
    @DisplayName("존재하지 않는 이벤트의 타입 조회 시 예외 발생")
    public void getEventTypeByIdWithNoEvent() {
        // given
        long eventId = 1L;
        given(eventRepository.findById(eventId)).willReturn(Optional.empty());

        // when
        NotFoundException exception = assertThrows(NotFoundException.class, () -> target.getEvent(eventId));

        // then
        verify(eventRepository).findById(eventId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
