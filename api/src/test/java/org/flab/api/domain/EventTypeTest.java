package org.flab.api.domain;

import org.flab.api.domain.event.domain.event.EventType;
import org.flab.api.global.exception.InvalidEventTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class EventTypeTest {

    @Test
    @DisplayName("이벤트 타입 검증")
    public void validateEventType() {
        // given
        EventType eventType = EventType.CONCERT;

        // when
        EventType result = EventType.validate(eventType.name());

        // then
        assertThat(result).isEqualTo(eventType);
    }

    @Test
    @DisplayName("이벤트 타입 검증 - 존재하지 않는 이름 조회 시 예외 발생")
    public void validateEventTypeInvalid() {
        // given
        String invalidType = "INVALID_TYPE";

        // when
        assertThatThrownBy(() -> EventType.validate(invalidType))
                .isInstanceOf(InvalidEventTypeException.class);
    }
}
