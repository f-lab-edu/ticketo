package org.flab.api.domain.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class Period {
    @Column(name="start_datetime", nullable = false)
    private final ZonedDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private final ZonedDateTime endDateTime;

    public ZonedDateTime getStartDateTime() {
        return ZonedDateTime.of(startDateTime.toLocalDateTime(), startDateTime.getZone());
    }

    public ZonedDateTime getEndDateTime() {
        return ZonedDateTime.of(endDateTime.toLocalDateTime(), endDateTime.getZone());
    }
}
