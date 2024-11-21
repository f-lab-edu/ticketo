package org.flab.api.domain.category.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.dto.SubCategoryResponse;
import org.flab.api.domain.event.dto.event.response.EventCategoryResponse;
import org.flab.api.global.common.Auditable;

import java.util.ArrayList;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category extends Auditable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    public CategoryResponse toResponse() {
        return new CategoryResponse(this.id,this.name, new ArrayList<>());
    }

    public SubCategoryResponse toSubCategoryResponse() {
        return new SubCategoryResponse(this.id,this.name);
    }

    public EventCategoryResponse toEventCategoryResponse() {
        return new EventCategoryResponse(this.parent.id, this.parent.name, new SubCategoryResponse(this.id, this.name));
    }

    public boolean isTopCategory() {
        return this.parent == null;
    }

}
