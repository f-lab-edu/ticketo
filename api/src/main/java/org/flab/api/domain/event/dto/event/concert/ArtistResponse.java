package org.flab.api.domain.event.dto.event.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.concert.Artist;

@Getter
@AllArgsConstructor
public class ArtistResponse {
    private long artistId;
    private String artistName;
    private String artistImage;

    public ArtistResponse(Artist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getName();
        this.artistImage = artist.getImage();
    }
}
