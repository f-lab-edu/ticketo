package org.flab.api.domain.event.dto.event.musical;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.show.ShowCast;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CharacterResponse {
    private Long characterId;
    private String characterName;
    private List<CastResponse> casts;

    public CharacterResponse(Character character, List<ShowCast> castList) {
       this.characterId = character.getId();
       this.characterName = character.getName();
       this.casts = castList.stream()
                            .map(CastResponse::new)
                            .toList();
    }
}
