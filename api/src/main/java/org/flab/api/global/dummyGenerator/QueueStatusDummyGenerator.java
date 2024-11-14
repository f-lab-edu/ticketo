package org.flab.api.global.dummyGenerator;

import org.flab.api.domain.queue.dto.QueueStatusResponse;

public class QueueStatusDummyGenerator {

    public static QueueStatusResponse generateQueueStatusDummyResponse() {
        return new QueueStatusResponse(834, 123213);
    }
}
