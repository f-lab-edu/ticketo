package org.flab.api.domain.event.dto.event.response.musical;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.musical.Actor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ActorResponse {
    private long actorId;
    private String actorName;
    private String actorImage;

    public ActorResponse(Actor actor) {
        this.actorId = actor.getId();
        this.actorName = actor.getName();
        this.actorImage = actor.getImage();
    }
}
