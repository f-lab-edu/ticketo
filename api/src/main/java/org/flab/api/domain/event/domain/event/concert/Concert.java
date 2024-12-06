package org.flab.api.domain.event.domain.event.concert;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.event.Event;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CONCERT")
public class Concert extends Event {

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventArtist> artistList;
}
