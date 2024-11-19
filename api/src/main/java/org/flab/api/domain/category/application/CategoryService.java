package org.flab.api.domain.category.application;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.repository.CategoryRepository;
import org.flab.api.global.exception.CustomException;
import org.flab.api.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryListResponse getCategoryListResponse() {
        List<CategoryResponse> categoryResponsesList = this.getCategoryResponseList();
        return new CategoryListResponse(categoryResponsesList.size(), categoryResponsesList);
    }

    private List<CategoryResponse> getCategoryResponseList() {
        List<Category> categoryList = categoryRepository.findAll();

        Map<Long, CategoryResponse> categoryMap = categoryList.stream()
                .filter(category -> category.getParentId() == null)
                .collect(Collectors.toMap(Category::getId, Category::toResponse));

        categoryList.stream()
                .filter(category -> category.getParentId() != null)
                .forEach(category -> {
                    CategoryResponse parent = categoryMap.get(category.getParentId());
                    if (parent == null) {
                        throw new CustomException(ErrorCode.TOP_CATEGORY_DOES_NOT_EXIST);
                    }
                    parent.getSubCategories().add(category.toSubCategoryResponse());
                });

        return new ArrayList<>(categoryMap.values());
    }
}
