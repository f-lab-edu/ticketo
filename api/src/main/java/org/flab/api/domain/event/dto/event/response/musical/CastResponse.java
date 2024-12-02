package org.flab.api.domain.event.dto.event.response.musical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.musical.Cast;

@AllArgsConstructor
@Getter
public class CastResponse {
    private Long castId;
    private String castName;
    private String castImage;

    public CastResponse(Cast cast) {
        this.castId = cast.getId();
        this.castName = cast.getName();
        this.castImage = cast.getImage();
    }
}
