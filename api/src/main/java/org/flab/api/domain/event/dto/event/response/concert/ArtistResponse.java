package org.flab.api.domain.event.dto.event.response.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.concert.Artist;
import org.flab.api.domain.event.domain.concert.EventArtist;

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

    public ArtistResponse(Artist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getName();
        this.artistImage = artist.getImage();
    }
}
