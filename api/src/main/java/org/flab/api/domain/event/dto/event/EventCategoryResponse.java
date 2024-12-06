package org.flab.api.domain.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.dto.SubCategoryResponse;

@AllArgsConstructor
@Getter
public class EventCategoryResponse {
    private long categoryId;
    private String categoryName;
    private SubCategoryResponse subCategory;

    public EventCategoryResponse(Category category) {
        this.categoryId = category.getParent().getId();
        this.categoryName = category.getParent().getName();
        this.subCategory = new SubCategoryResponse(category);
    }
}
