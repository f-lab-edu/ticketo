package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventPlaceResponse {
    private long placeId;
    private String placeName;
}
