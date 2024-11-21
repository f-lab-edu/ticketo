package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EventCastResponse {
    private Long castId;
    private String castName;
    private String castImage;
    private Long characterId;
    private String characterName;
}
