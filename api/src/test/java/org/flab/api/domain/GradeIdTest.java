package org.flab.api.domain;

import org.flab.api.domain.event.domain.seat.GradeId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeIdTest {

    @Test
    public void GradeIdTest() {
        long id = 1L;

        GradeId gradeId1 = new GradeId(id);
        GradeId gradeId2 = new GradeId(id);

        assertTrue(gradeId1.equals(gradeId2));
    }
}
