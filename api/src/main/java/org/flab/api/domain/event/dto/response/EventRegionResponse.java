package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventRegionResponse {
    private long regionId;
    private String regionName;
}
