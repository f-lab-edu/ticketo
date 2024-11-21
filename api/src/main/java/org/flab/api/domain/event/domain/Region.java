package org.flab.api.domain.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.flab.api.domain.event.dto.event.response.EventRegionResponse;
import org.flab.api.global.common.Auditable;

@Getter
@Entity
@Table(name = "region")
public class Region extends Auditable  {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public EventRegionResponse toEventRegionResponse() {
        return new EventRegionResponse(id, name);
    }
}
