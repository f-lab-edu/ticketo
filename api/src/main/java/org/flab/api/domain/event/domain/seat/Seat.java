package org.flab.api.domain.event.domain.seat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.show.Show;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Column(name="row_number")
    private Long rowNumber;

    @Column(name = "col_number")
    private Long colNumber;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private SeatStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public Seat(Show show, Zone zone, Long rowNumber, Long colNumber, SeatStatus status, ZonedDateTime createdAt) {
        this.show = show;
        this.zone = zone;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.status = status;
        this.createdAt = createdAt;
    }
}
