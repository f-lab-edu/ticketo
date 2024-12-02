package org.flab.api.domain.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class Image {
    @Column(name = "thumbnail_img")
    private final String thumbnailImageUrl;

    @Column(name = "poster_img")
    private final String posterImageUrl;

}
