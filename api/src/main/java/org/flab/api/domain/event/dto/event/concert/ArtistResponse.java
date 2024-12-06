package org.flab.api.domain.event.dto.event.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.concert.EventArtist;
import org.flab.api.domain.event.domain.show.ShowArtist;

@Getter
@AllArgsConstructor
public class ArtistResponse {
    private long artistId;
    private String artistName;
    private String artistImage;

    public ArtistResponse(EventArtist artist) {
        this.artistId = artist.getArtist().getId();
        this.artistName = artist.getArtist().getName();
        this.artistImage = artist.getArtist().getImage();
    }

    public ArtistResponse(ShowArtist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getEventArtist().getArtist().getName();
        this.artistImage = artist.getEventArtist().getArtist().getImage();
    }
}
