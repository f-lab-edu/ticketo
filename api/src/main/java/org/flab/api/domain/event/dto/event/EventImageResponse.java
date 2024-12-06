package org.flab.api.domain.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.Image;

@AllArgsConstructor
@Getter
public class EventImageResponse {
    private String thumbnailImageUrl;
    private String posterImageUrl;

    public EventImageResponse(Image image) {
        this.thumbnailImageUrl = image.getThumbnailImageUrl();
        this.posterImageUrl = image.getPosterImageUrl();
    }
}
