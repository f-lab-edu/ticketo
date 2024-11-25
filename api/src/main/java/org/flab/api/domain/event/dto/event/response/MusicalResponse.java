package org.flab.api.domain.event.dto.event.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class MusicalResponse extends EventResponse {
    private Integer interMissionTime;
    private List<CharacterResponse> casts;
}
