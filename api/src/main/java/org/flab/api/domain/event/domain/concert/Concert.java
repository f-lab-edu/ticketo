package org.flab.api.domain.event.domain.concert;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.flab.api.domain.event.domain.Event;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CONCERT")
public class Concert extends Event {

}
