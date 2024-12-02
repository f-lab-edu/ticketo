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
import org.hibernate.annotations.BatchSize;

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
    private List<Character> characterList;
}
