package org.flab.api.domain.event.dto.event.response.musical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.musical.Character;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CharacterResponse {
    private Long characterId;
    private String characterName;
    private List<CastResponse> casts;

    public CharacterResponse(Character character) {
       this.characterId = character.getId();
       this.characterName = character.getName();
       this.casts = character.getCastList().stream()
                            .map(CastResponse::new)
                            .toList();
    }
}
