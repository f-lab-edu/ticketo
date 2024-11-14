package org.flab.api.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CategoryResponse {
     private long categoryId;
     private String categoryName;
     private List<SubCategoryResponse> subCategories;
}
