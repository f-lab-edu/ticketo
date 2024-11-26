package org.flab.api.service;

import org.flab.api.domain.event.domain.EventType;
import org.flab.api.domain.event.domain.musical.Musical;
import org.flab.api.domain.event.repository.musical.MusicalRepository;
import org.flab.api.domain.event.service.MusicalService;
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
public class MusicalServiceTest {

    @Mock
    private MusicalRepository musicalRepository;

    @InjectMocks
    private MusicalService target;

    private final long musicalId = 2L;
    private final long notFoundId = 404L;

    @Test
    @DisplayName("뮤지컬 조회")
    public void getMusicalTest() {
        // given
        Musical mockMusical = mock(Musical.class);
        when(mockMusical.getId()).thenReturn(musicalId);
        when(mockMusical.getType()).thenReturn(EventType.MUSICAL);
        given(musicalRepository.findMusicalWithRelationEntity(musicalId)).willReturn(Optional.of(mockMusical));

        // when
        Musical result = target.getEvent(musicalId);

        // then
        assertThat(result.getId()).isEqualTo(musicalId);
        assertThat(result.getType()).isEqualTo(EventType.MUSICAL);
    }

    @Test
    @DisplayName("존재하지 않는 뮤지컬 조회")
    public void getEventNotFoundTest() {
        // given
        given(musicalRepository.findMusicalWithRelationEntity(notFoundId)).willReturn(Optional.empty());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> target.getEvent(notFoundId));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_NOT_FOUND);
    }
}
