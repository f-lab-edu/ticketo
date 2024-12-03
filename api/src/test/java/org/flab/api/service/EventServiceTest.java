package org.flab.api.service;

import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.InvalidEventTypeException;
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
    @DisplayName("이벤트 타입 조회")
    public void getEventType() {
        // given
        long eventId = 1L;
        EventType type = EventType.CONCERT;
        given(eventRepository.findEventTypeById(eventId)).willReturn(Optional.of(type));

        // when
        EventType eventType = target.getTypeById(eventId);

        // then
        verify(eventRepository).findEventTypeById(eventId);
        assertThat(eventType.name()).isEqualTo(EventType.CONCERT.name());
    }

    @Test
    @DisplayName("유효하지 않은 이벤트 타입 조회 시 예외 발생")
    public void getEventTypeByIdWithInvalidEventType() {
        // given
        long eventId = 1L;
        EventType type = mock(EventType.class);
        given(type.name()).willReturn("INVALID_TYPE");
        given(eventRepository.findEventTypeById(eventId)).willReturn(Optional.of(type));

        // when
        InvalidEventTypeException exception = assertThrows(InvalidEventTypeException.class, () -> target.getTypeById(eventId));

        // then
        verify(eventRepository).findEventTypeById(eventId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.INVALID_EVENT_TYPE);
    }

    @Test
    @DisplayName("존재하지 않는 이벤트의 타입 조회 시 예외 발생")
    public void getEventTypeByIdWithNoEvent() {
        // given
        long eventId = 1L;
        given(eventRepository.findEventTypeById(eventId)).willReturn(Optional.empty());

        // when
        NotFoundException exception = assertThrows(NotFoundException.class, () -> target.getTypeById(eventId));

        // then
        verify(eventRepository).findEventTypeById(eventId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
