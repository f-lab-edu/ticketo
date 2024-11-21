package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlaceResponse {
    private long placeId;
    private String placeName;
}
