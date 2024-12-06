package org.flab.api.domain.event.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NullDataException;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class Period {
    @Column(name="start_datetime", nullable = false)
    private final ZonedDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private final ZonedDateTime endDateTime;

    public Period(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        if(startDateTime == null || endDateTime == null) {
            throw new NullDataException(ErrorCode.NULL_PERIOD);
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDateTime, period.startDateTime) && Objects.equals(endDateTime, period.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }
}
