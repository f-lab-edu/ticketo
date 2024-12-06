package org.flab.api.domain.event.dto.event.musical;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.musical.Musical;
import org.flab.api.domain.event.dto.event.EventCategoryResponse;
import org.flab.api.domain.event.dto.event.EventImageResponse;
import org.flab.api.domain.event.dto.event.EventResponse;
import org.flab.api.domain.event.dto.event.PlaceResponse;
import org.flab.api.domain.event.dto.event.RegionResponse;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicalResponse extends EventResponse {
    private Integer interMissionTime;
    private List<CharacterResponse> casts;

    public MusicalResponse(Musical musical) {
        super(musical.getId(), musical.getName(), musical.getType(), musical.getEventPeriod().getStartDateTime(), musical.getEventPeriod().getEndDateTime(),
        musical.getRunningTime(), musical.getDescription(), musical.getReservationPeriod().getStartDateTime(), musical.getReservationPeriod().getEndDateTime(),
        musical.getHasPreReservation(), musical.getPreReservationPeriod().getStartDateTime(), musical.getPreReservationPeriod().getEndDateTime(),
                new EventCategoryResponse(musical.getCategory()), new PlaceResponse(musical.getPlace()),
                new RegionResponse(musical.getRegion()), new EventImageResponse(musical.getImage())
        );

        this.interMissionTime= musical.getIntermissionTime();
        this.casts = musical.getCharacterList().stream()
                        .map(CharacterResponse::new)
                        .toList();
    }
}
