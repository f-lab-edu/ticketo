package org.flab.api.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubCategoryResponse {
    private long subCategoryId;
    private String subCategoryName;
}
