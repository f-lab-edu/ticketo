package org.flab.api.domain.event.dto.show;

import lombok.Getter;
import org.flab.api.domain.event.domain.event.concert.Artist;
import org.flab.api.domain.event.domain.show.ShowCast;

@Getter
public class ParticipantResponse {

    private Long id;
    private String name;
    private String image;

    public ParticipantResponse(ShowCast showCast) {
        this.id = showCast.getActor().getId();
        this.name = showCast.getActor().getName();
        this.image = showCast.getActor().getImage();
    }

    public ParticipantResponse(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.image = artist.getImage();
    }
}