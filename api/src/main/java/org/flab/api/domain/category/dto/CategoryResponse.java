package org.flab.api.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.api.domain.category.domain.Category;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class CategoryResponse {
     private long categoryId;
     private String categoryName;
     private List<SubCategoryResponse> subCategories;

     public CategoryResponse(Category category) {
         this.categoryId = category.getId();
         this.categoryName = category.getName();
         this.subCategories = new ArrayList<>();
     }
}
