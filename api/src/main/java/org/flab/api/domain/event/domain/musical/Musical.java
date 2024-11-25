package org.flab.api.domain.event.domain.musical;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.Event;
import org.flab.api.domain.event.dto.event.response.CharacterResponse;
import org.flab.api.domain.event.dto.event.response.MusicalResponse;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("MUSICAL")
public class Musical extends Event {

    @Column(name = "intermission_time")
    private Integer intermissionTime;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Character> characterList = new ArrayList<>();

    public MusicalResponse toResponse(List<CharacterResponse> characterResponses) {
        return MusicalResponse.builder()
                .eventId(super.getId())
                .eventName(super.getName())
                .eventType(super.getType())
                .runningTime(super.getRunningTime())
                .interMissionTime(intermissionTime)
                .description(super.getDescription())
                .eventStartDate(super.getEventPeriod().getStartDateTime())
                .eventEndDate(super.getEventPeriod().getEndDateTime())
                .reservationStartDateTime(super.getReservationPeriod().getStartDateTime())
                .reservationEndDateTime(super.getReservationPeriod().getEndDateTime())
                .category(super.getCategory().toEventCategoryResponse())
                .place(super.getPlace().toEventPlaceResponse())
                .region(super.getRegion().toEventRegionResponse())
                .image(super.getImage().toEventImageResponse())
                .casts(characterResponses)
                .build();
    }

}
