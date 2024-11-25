package org.flab.api.domain.event.dto.event.response.musical;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CastResponse {
    private Long castId;
    private String castName;
    private String castImage;
}
