package org.flab.api.domain.event.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequestParams {
    private Long regionId;
    @NotNull
    private String category;

    @Override
    public String toString() {
        return "EventRequestParams{" + "regionId=" + regionId + ", category='" + category + '\'' + '}';
    }
}
