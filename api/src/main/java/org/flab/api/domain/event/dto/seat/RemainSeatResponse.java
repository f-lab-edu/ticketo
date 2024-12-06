package org.flab.api.domain.event.dto.seat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.seat.Grade;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RemainSeatResponse {
    private long gradeId;
    private String gradeName;
    private long count;

    public RemainSeatResponse(Grade grade, long count) {
        this.gradeId = grade.getId();
        this.gradeName = grade.getName();
        this.count = count;
    }
}
