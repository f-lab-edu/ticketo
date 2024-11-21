package org.flab.api.domain.event.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CharacterResponse {
    private Long characterId;
    private String characterName;
    private List<CastResponse> casts;
}
