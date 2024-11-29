package org.flab.api.service;

import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.domain.Show;
import org.flab.api.domain.event.repository.show.ShowRepository;
import org.flab.api.domain.event.service.ShowService;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowServiceTest {

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowService target;

    private final  long eventId = 1L;

    @Test
    @DisplayName("이벤트의 회차 목록 조회")
    public void getShowListTest() {
        // given
        Show mockShow1 = mock(Show.class);
        Show mockShow2 = mock(Show.class);
        Show mockShow3 = mock(Show.class);

        Event mockEvent = mock(Event.class);
        when(mockEvent.getId()).thenReturn(eventId);

        when(mockShow1.getEvent()).thenReturn(mockEvent);
        when(mockShow2.getEvent()).thenReturn(mockEvent);
        when(mockShow3.getEvent()).thenReturn(mockEvent);

        List<Show> showList = List.of(mockShow1, mockShow2, mockShow3);
        given(showRepository.findShowsByEventId(eventId)).willReturn(showList);

        // when
        List<Show> result = target.getShowListByEventId(eventId);

        // then
        assertThat(result.size()).isEqualTo(showList.size());
        for(Show show : result) {
            assertThat(show.getEvent().getId()).isEqualTo(eventId);
        }
    }

    @Test
    @DisplayName("이벤트의 회차 목록 조회 - 회차 없는 이벤트")
    public void getShowListTestWithoutShows() {
        // given
        given(showRepository.findShowsByEventId(eventId)).willReturn(List.of());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> target.getShowListByEventId(eventId));

        // then
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EVENT_HAS_NO_SHOW);
    }
}
