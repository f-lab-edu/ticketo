package org.flab.api.domain.event.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class NullablePeriod {
    @Column(name="start_datetime")
    private final ZonedDateTime startDateTime;

    @Column(name = "end_datetime")
    private final ZonedDateTime endDateTime;

    public NullablePeriod(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NullablePeriod period = (NullablePeriod) o;
        return Objects.equals(startDateTime, period.startDateTime) && Objects.equals(endDateTime, period.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }
}
