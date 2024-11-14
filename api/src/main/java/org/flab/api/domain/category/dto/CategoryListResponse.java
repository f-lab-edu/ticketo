package org.flab.api.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CategoryListResponse {
    private int totalCount;
    private List<CategoryResponse> categories;
}
