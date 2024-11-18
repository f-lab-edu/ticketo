package org.flab.api.domain.event.dto.event.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.flab.api.domain.category.domain.CategoryType;

@Getter
@Setter
public class EventRequestParams {
    private Long regionId;
    @NotNull
    private CategoryType categoryType;

    @Override
    public String toString() {
        return "EventRequestParams{" + "regionId=" + regionId + ", categoryType='" + categoryType + '\'' + '}';
    }
}
