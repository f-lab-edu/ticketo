package org.flab.api.domain.event.dto.event.musical;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Character;
import org.flab.api.domain.event.domain.event.musical.Musical;
import org.flab.api.domain.event.domain.show.ShowCast;
import org.flab.api.domain.event.dto.event.EventCategoryResponse;
import org.flab.api.domain.event.dto.event.EventImageResponse;
import org.flab.api.domain.event.dto.event.EventResponse;
import org.flab.api.domain.place.dto.PlaceResponse;
import org.flab.api.domain.region.dto.RegionResponse;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicalResponse extends EventResponse {
    private Integer interMissionTime;
    private List<CharacterResponse> casts;

    public MusicalResponse(Musical musical, List<Character> characterList, List<ShowCast> castList) {
        super(musical.getId(), musical.getName(), musical.getType(), musical.getEventPeriod().getStartDateTime(), musical.getEventPeriod().getEndDateTime(),
                musical.getRunningTime(), musical.getDescription(), musical.getReservationPeriod().getStartDateTime(), musical.getReservationPeriod().getEndDateTime(),
                musical.getHasPreReservation(),
                musical.getHasPreReservation() ? musical.getPreReservationPeriod().getStartDateTime() : null,
                musical.getHasPreReservation() ? musical.getPreReservationPeriod().getEndDateTime() : null,
                new EventCategoryResponse(musical.getCategory()), new PlaceResponse(musical.getPlace()),
                new RegionResponse(musical.getRegion()), new EventImageResponse(musical.getImage())
        );
        this.interMissionTime= musical.getIntermissionTime();
        this.casts = characterList.stream()
                        .map(character -> new CharacterResponse(character, castList))
                        .toList();
    }
}
