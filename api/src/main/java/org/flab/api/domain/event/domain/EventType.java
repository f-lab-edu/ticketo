package org.flab.api.domain.event.domain;

import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;

import java.util.Arrays;

public enum EventType {
    CONCERT, MUSICAL;

    public static EventType validateEventType(String eventType) {
        return Arrays.stream(EventType.values())
                .filter(type -> type.name().equalsIgnoreCase(eventType))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EVENT_TYPE));
    }
}
