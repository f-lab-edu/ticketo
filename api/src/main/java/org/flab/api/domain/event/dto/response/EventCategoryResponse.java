package org.flab.api.domain.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.category.dto.SubCategoryResponse;

@AllArgsConstructor
@Getter
public class EventCategoryResponse {
    private long categoryId;
    private String categoryName;
    private SubCategoryResponse subCategory;
}
