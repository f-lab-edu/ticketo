package org.flab.api.service;

import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.concert.Concert;
import org.flab.api.domain.event.repository.concert.ConcertRepository;
import org.flab.api.domain.event.service.ConcertService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertService target;

    private final long concertId = 1L;
    private final long notFoundId = 404L;

    @Test
    @DisplayName("콘서트 조회")
    public void getConcertTest() {
        // given
        Concert mockConcert = mock(Concert.class);
        when(mockConcert.getId()).thenReturn(concertId);
        when(mockConcert.getType()).thenReturn(EventType.CONCERT);
        given(concertRepository.findConcertWithRelationEntity(concertId)).willReturn(Optional.of(mockConcert));

        // when
        Concert result = target.getEvent(concertId);

        // then
        verify(concertRepository).findConcertWithRelationEntity(concertId);
        assertThat(result.getId()).isEqualTo(concertId);
        assertThat(result.getType()).isEqualTo(EventType.CONCERT);
    }

    @Test
    @DisplayName("존재하지 않는 이벤트 조회")
    public void getEventNotFoundTest() {
        // given
        given(concertRepository.findConcertWithRelationEntity(notFoundId)).willReturn(Optional.empty());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> target.getEvent(notFoundId));

        // then
        verify(concertRepository).findConcertWithRelationEntity(notFoundId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
