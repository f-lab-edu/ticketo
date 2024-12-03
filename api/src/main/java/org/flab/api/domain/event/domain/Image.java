package org.flab.api.domain.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.global.exception.ErrorCode;
import org.flab.api.global.exception.NullDataException;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Embeddable
public class Image {
    @Column(name = "thumbnail_img")
    private final String thumbnailImageUrl;

    @Column(name = "poster_img")
    private final String posterImageUrl;

    public Image(String thumbnailImageUrl, String posterImageUrl) {
        if (thumbnailImageUrl == null || posterImageUrl == null) {
            throw new NullDataException(ErrorCode.NULL_IMAGE);
        }
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.posterImageUrl = posterImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(thumbnailImageUrl, image.thumbnailImageUrl) && Objects.equals(posterImageUrl, image.posterImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnailImageUrl, posterImageUrl);
    }
}
