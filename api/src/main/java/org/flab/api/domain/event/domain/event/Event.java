package org.flab.api.domain.event.domain.event;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.place.domain.Place;
import org.flab.api.domain.region.domain.Region;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.flab.api.domain.event.domain.seat.Grade;
import org.hibernate.annotations.Formula;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name = "event")
public class Event {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Formula("(type)")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column(name = "description")
    private String description;

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

    @Column(name = "has_pre_reservation")
    private Boolean hasPreReservation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDateTime", column = @Column(name = "pre_reservation_start_datetime"))
            , @AttributeOverride(name = "endDateTime", column = @Column(name = "pre_reservation_end_datetime"))
    })
    private NullablePeriod preReservationPeriod = new NullablePeriod();

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

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Grade> gradeList;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<DiscountPolicy> discountPolicyList;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
