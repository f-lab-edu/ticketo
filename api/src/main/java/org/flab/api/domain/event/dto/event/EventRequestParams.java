package org.flab.api.domain.event.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.flab.api.domain.event.domain.event.EventType;

@Getter
@Setter
public class EventRequestParams {
    private Long regionId;
    @NotNull
    private EventType eventType;
}
