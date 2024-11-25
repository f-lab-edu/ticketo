package org.flab.api.service;

import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.repository.EventRepository;
import org.flab.api.domain.event.service.EventService;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService target;

    private long musicalId = 1L;
    private long concertId = 2L;
    private final long notFoundId = 3L;

    @Test
    @DisplayName("뮤지컬 조회")
    public void getMusicalTest() {
        // given
        Musical mockMusical = mock(Musical.class);
        when(mockMusical.getId()).thenReturn(musicalId);
        when(mockMusical.getType()).thenReturn(EventType.MUSICAL);
        given(eventRepository.findEventWithRelationEntity(musicalId)).willReturn(Optional.of(mockMusical));

        // when
        Event result = target.getEvent(musicalId);

        // then
        assertThat(result.getId()).isEqualTo(musicalId);
        assertThat(result.getType()).isEqualTo(EventType.MUSICAL);
    }

    @Test
    @DisplayName("콘서트 조회")
    public void getConcertTest() {
        // given
        Concert mockConcert = mock(Concert.class);
        when(mockConcert.getId()).thenReturn(concertId);
        when(mockConcert.getType()).thenReturn(EventType.CONCERT);

        given(eventRepository.findEventWithRelationEntity(concertId)).willReturn(Optional.of(mockConcert));

        // when
        Event result = target.getEvent(concertId);

        // then
        assertThat(result.getId()).isEqualTo(concertId);
        assertThat(result.getType()).isEqualTo(EventType.CONCERT);
    }

    @Test
    @DisplayName("존재하지 않는 이벤트 조회")
    public void getEventNotFoundTest() {
        // given
        given(eventRepository.findEventWithRelationEntity(notFoundId)).willReturn(Optional.empty());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> target.getEvent(notFoundId));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
