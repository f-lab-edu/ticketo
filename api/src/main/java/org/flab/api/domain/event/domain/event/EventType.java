package org.flab.api.domain.event.domain.event;

import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.InvalidEventTypeException;

import java.util.Arrays;

public enum EventType {
    CONCERT, MUSICAL;

    public static EventType validate(String eventType) {
        return Arrays.stream(EventType.values())
                .filter(type -> type.name().equalsIgnoreCase(eventType))
                .findFirst()
                .orElseThrow(() -> new InvalidEventTypeException(ErrorCode.INVALID_EVENT_TYPE));
    }
}
