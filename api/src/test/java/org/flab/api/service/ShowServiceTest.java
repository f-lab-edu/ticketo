package org.flab.api.service;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.show.Show;
import org.flab.api.domain.event.repository.show.ShowRepository;
import org.flab.api.domain.event.service.ShowService;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NotFoundException;
import org.flab.api.global.exception.InvalidShowException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowServiceTest {

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowService target;

    @Test
    @DisplayName("이벤트의 회차 목록 조회")
    public void getShowListTest() {
        // given
        long eventId = 1L;
        List<Show> showList = createShowListMock(eventId);
        given(showRepository.findAllByEventId(eventId)).willReturn(showList);

        // when
        List<Show> result = target.getShowListByEventId(eventId);

        // then
        verify(showRepository).findAllByEventId(eventId);
        assertThat(result.size()).isEqualTo(showList.size());
        for(Show show : result) {
            assertThat(show.getEvent().getId()).isEqualTo(eventId);
        }
    }

    @Test
    @DisplayName("이벤트의 회차 목록 조회 - 회차 없는 이벤트")
    public void getShowListTestWithoutShows() {
        // given
        long eventId = 1L;
        given(showRepository.findAllByEventId(eventId)).willReturn(List.of());

        // when
        InvalidShowException exception = assertThrows(InvalidShowException.class, () -> target.getShowListByEventId(eventId));

        // then
        verify(showRepository).findAllByEventId(eventId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_HAS_NO_SHOW);
    }

    @Test
    @DisplayName("회차 조회")
    public void getShowByIdAndEventIdTest() {
        // given
        long showId = 1L;
        long eventId = 1L;
        Show showMock = mock(Show.class);
        Event eventMock = mock(Event.class);
        given(eventMock.getId()).willReturn(eventId);
        given(showMock.getEvent()).willReturn(eventMock);
        given(showRepository.findById(showId)).willReturn(Optional.of(showMock));

        // when
        Show show = target.getShowByIdAndEventId(showId, eventId);

        // then
        verify(showRepository).findById(showId);
        assertThat(show.getEvent().getId()).isEqualTo(eventId);

    }

    @Test
    @DisplayName("회차 조회 - 없는 회차 조회")
    public void getShowByIdAndEventIdTestNotShow() {
        // given
        given(showRepository.findById(anyLong())).willReturn(Optional.empty());

        // when
        NotFoundException exception = assertThrows(NotFoundException.class, () -> target.getShowByIdAndEventId(404L, 1L));

        // then
        verify(showRepository).findById(anyLong());
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SHOW_NOT_FOUND);
    }

    @Test
    @DisplayName("회차 조회 - 다른_이벤트_아이디로_조회")
    public void 다른_이벤트_아이디로_조회() {
        // given
        long showId = 1L;
        long actualEventId = 2L;
        long wrongEventId = 1L;
        Show showMock = mock(Show.class);
        Event eventMock = mock(Event.class);
        given(eventMock.getId()).willReturn(actualEventId);
        given(showMock.getEvent()).willReturn(eventMock);
        given(showRepository.findById(showId)).willReturn(Optional.of(showMock));

        // when
        InvalidShowException exception = assertThrows(InvalidShowException.class, () -> target.getShowByIdAndEventId(showId, wrongEventId));

        // then
        verify(showRepository).findById(showId);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.INVALID_EVENT_SHOW);
    }

    private List<Show> createShowListMock(long eventId) {
        Show mockShow1 = mock(Show.class);
        Show mockShow2 = mock(Show.class);
        Show mockShow3 = mock(Show.class);

        Event mockEvent = mock(Event.class);
        when(mockEvent.getId()).thenReturn(eventId);

        when(mockShow1.getEvent()).thenReturn(mockEvent);
        when(mockShow2.getEvent()).thenReturn(mockEvent);
        when(mockShow3.getEvent()).thenReturn(mockEvent);

        return List.of(mockShow1, mockShow2, mockShow3);
    }
}
