package org.flab.api.domain.category.domain;

import jakarta.persistence.*;
import lombok.*;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.dto.SubCategoryResponse;
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
    @Column(name = "parent_id")
    private Long parentId;

    public CategoryResponse toResponse() {
        return new CategoryResponse(this.id,this.name, new ArrayList<>());
    }

    public SubCategoryResponse toSubCategoryResponse() {
        return new SubCategoryResponse(this.id,this.name);
    }
}
