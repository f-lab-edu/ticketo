package org.flab.api.domain.event.dto.event.musical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.show.ShowCast;

@AllArgsConstructor
@Getter
public class CastResponse {
    private Long castId;
    private ActorResponse actor;

    public CastResponse(ShowCast showCast) {
        this.castId = showCast.getId();
        this.actor = new ActorResponse(showCast.getActor());
    }
}
