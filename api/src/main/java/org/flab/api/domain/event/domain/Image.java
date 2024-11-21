package org.flab.api.domain.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.dto.event.response.EventImageResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class Image {
    @Column(name = "thumbnail_img")
    private final String thumbnailImageUrl;

    @Column(name = "poster_img")
    private final String posterImageUrl;

    public EventImageResponse toEventImageResponse() {
        return new EventImageResponse(thumbnailImageUrl, posterImageUrl);
    }
}
