package org.flab.api.domain.event.domain.show;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.event.Period;
import org.flab.api.domain.event.domain.seat.Seat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Entity
@Table(name="show")
@EntityListeners(AuditingEntityListener.class)
public class Show {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "show_datetime")
    private ZonedDateTime showDateTime;

    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Seat> seatList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDateTime", column = @Column(name = "reservation_start_datetime"))
            , @AttributeOverride(name = "endDateTime", column = @Column(name = "reservation_end_datetime"))
    })
    private Period reservationPeriod;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private ZonedDateTime updatedAt;

}
