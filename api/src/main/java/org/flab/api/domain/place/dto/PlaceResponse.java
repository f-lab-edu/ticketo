package org.flab.api.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.place.domain.Place;

@AllArgsConstructor
@Getter
public class PlaceResponse {
    private long placeId;
    private String placeName;

    public PlaceResponse(Place place) {
        this.placeId = place.getId();
        this.placeName = place.getName();
    }
}
