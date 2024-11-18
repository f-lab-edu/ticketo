package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventCastResponse {
    private long characterId;
    private String characterName;
    private long manId;
    private String manName;
    private String imageFileUrl;
}
