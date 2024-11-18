package org.flab.api.domain.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QueueStatusResponse {
    private long rank;
    private long totalCount;
}
