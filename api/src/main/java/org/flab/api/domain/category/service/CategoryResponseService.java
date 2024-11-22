package org.flab.api.domain.category.service;

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
public class CategoryResponseService {

    private final CategoryRepository categoryRepository;

    public CategoryListResponse getCategoryListResponse() {
        List<CategoryResponse> categoryResponsesList = this.getCategoryResponseList();
        return new CategoryListResponse(categoryResponsesList.size(), categoryResponsesList);
    }

    private List<CategoryResponse> getCategoryResponseList() {
        List<Category> categoryList = categoryRepository.findAll();
        Map<Long, CategoryResponse> topCategoryResponseMap = getTopCategoryMap(categoryList);
        this.addSubCategoryResponse(categoryList, topCategoryResponseMap);
        return new ArrayList<>(topCategoryResponseMap.values());
    }

    private Map<Long, CategoryResponse> getTopCategoryMap(List<Category> categoryList) {
        return categoryList.stream()
                .filter(Category::isTopCategory)
                .collect(Collectors.toMap(Category::getId, Category::toResponse));
    }

    private void addSubCategoryResponse(List<Category> categoryList, Map<Long, CategoryResponse> categoryMap) {
        categoryList.stream()
                .filter(category -> !category.isTopCategory())
                .forEach(category -> {
                    CategoryResponse parent = categoryMap.get(category.getParent().getId());
                    if (parent == null) {
                        throw new CustomException(ErrorCode.TOP_CATEGORY_NOT_FOUND);
                    }
                    parent.getSubCategories().add(category.toSubCategoryResponse());
                });
    }
}
