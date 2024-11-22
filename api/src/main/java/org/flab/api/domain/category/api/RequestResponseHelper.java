package org.flab.api.domain.category.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.domain.CategoryList;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.dto.SubCategoryResponse;
import org.flab.api.domain.category.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestResponseHelper {

    private final CategoryService categoryService;

    public CategoryListResponse getCategoryListResponse() {
        List<CategoryResponse> categoryResponsesList = this.createCategoryResponseList();
        return new CategoryListResponse(categoryResponsesList.size(), categoryResponsesList);
    }

    private List<CategoryResponse> createCategoryResponseList() {
        CategoryList categoryList = categoryService.getAllCategoryList();
        Map<Long, CategoryResponse> topCategoryResponseMap = this.createTopCategoryResponseMap(categoryList);
        this.addSubCategoryResponseList(categoryList, topCategoryResponseMap);
        return new ArrayList<>(topCategoryResponseMap.values());
    }

    private Map<Long, CategoryResponse> createTopCategoryResponseMap(CategoryList categoryList) {
        List<Category> topCategoryList = categoryList.getTopCategoryList();
        return topCategoryList.stream().collect(Collectors.toMap(Category::getId, Category::toResponse));
    }

    private void addSubCategoryResponseList(CategoryList categoryList, Map<Long, CategoryResponse> topCategoryResponseMap) {
        topCategoryResponseMap.forEach((categoryId, categoryResponse) -> {
            List<Category> subCategoryList = categoryList.getSubCategoryListByParentId(categoryId);
            List<SubCategoryResponse> subCategoryResponseList = subCategoryList.stream().map(Category::toSubCategoryResponse).collect(Collectors.toList());
            topCategoryResponseMap.get(categoryId).getSubCategories().addAll(subCategoryResponseList);
        });
    }
}
