package org.flab.api.domain.event.dto.event.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.flab.api.domain.event.domain.EventType;

@Getter
@Setter
public class EventRequestParams {
    private Long regionId;
    @NotNull
    private EventType eventType;
}
