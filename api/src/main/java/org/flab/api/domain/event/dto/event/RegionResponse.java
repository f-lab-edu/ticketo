package org.flab.api.domain.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.Region;

@AllArgsConstructor
@Getter
public class RegionResponse {
    private long regionId;
    private String regionName;

    public RegionResponse(Region region) {
        this.regionId = region.getId();
        this.regionName = region.getName();
    }
}
