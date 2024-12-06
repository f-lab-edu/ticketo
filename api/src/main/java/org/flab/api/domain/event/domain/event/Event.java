package org.flab.api.domain.event.domain.event;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.flab.api.domain.event.domain.seat.Grade;
import org.flab.api.domain.event.domain.seat.Zone;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name = "event")
@EntityListeners(AuditingEntityListener.class)
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
    private PreReservationPeriod preReservationPeriod = new PreReservationPeriod();

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
    private List<Zone> zoneList;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<DiscountPolicy> discountPolicyList;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private ZonedDateTime updatedAt;

    @PostLoad
    private void initializeEmbeddable() {
        if (Objects.isNull(preReservationPeriod)) {
            preReservationPeriod = new PreReservationPeriod();
        }
    }
}
