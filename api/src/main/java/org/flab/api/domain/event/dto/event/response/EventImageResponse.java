package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventImageResponse {
    private String thumbnailImageUrl;
    private String posterImageUrl;
}
