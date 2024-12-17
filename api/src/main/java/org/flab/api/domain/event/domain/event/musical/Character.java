package org.flab.api.domain.event.domain.event.musical;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.show.ShowCast;
import org.hibernate.annotations.BatchSize;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
    private List<ShowCast> showCastList;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
