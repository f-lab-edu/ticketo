package org.flab.api.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.api.domain.category.domain.Category;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubCategoryResponse {
    private long subCategoryId;
    private String subCategoryName;

    public SubCategoryResponse(Category category) {
        this.subCategoryId = category.getId();
        this.subCategoryName = category.getName();
    }
}
