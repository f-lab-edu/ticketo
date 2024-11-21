package org.flab.api.domain.event.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.event.dto.event.response.EventCastResponse;
import org.flab.api.domain.event.dto.event.response.EventResponse;
import org.flab.api.global.common.Auditable;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event extends Auditable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "intermission_time")
    private Integer intermissionTime;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column(name = "description")
    private String description;

    @Column(name = "has_pre_reservation")
    private Boolean hasPreReservation;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDateTime", column = @Column(name = "pre_reservation_start_datetime"))
        , @AttributeOverride(name = "endDateTime", column = @Column(name = "pre_reservation_end_datetime"))
    })
    private Period preReservationStartDateTime;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDateTime", column = @Column(name = "event_start_datetime"))
        , @AttributeOverride(name = "endDateTime", column = @Column(name = "event_end_datetime"))
    })
    private Period eventPeriod;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDateTime", column = @Column(name = "reservation_start_datetime"))
        , @AttributeOverride(name = "endDateTime", column = @Column(name = "reservation_end_datetime"))
    })
    private Period reservationPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id")
    private Region region;

    @Embedded
    private Image image;

    @Builder.Default
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Character> characterList = new ArrayList<>();

    public EventResponse toResponse(List<EventCastResponse> castList) {
        return new EventResponse(id, name, type , eventPeriod.getStartDateTime(), eventPeriod.getEndDateTime()
                , runningTime, intermissionTime, description, reservationPeriod.getStartDateTime(), reservationPeriod.getEndDateTime()
                , hasPreReservation, preReservationStartDateTime.getStartDateTime(), preReservationStartDateTime.getStartDateTime()
                , category.toEventCategoryResponse()
                , place.toEventPlaceResponse()
                , region.toEventRegionResponse()
                , image.toEventImageResponse()
                , castList
        );
    }
}
