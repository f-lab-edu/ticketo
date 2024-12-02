package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.Region;

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
