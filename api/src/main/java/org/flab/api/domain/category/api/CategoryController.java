package org.flab.api.domain.category.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.domain.CategoryList;
import org.flab.api.domain.category.dto.CategoryListResponse;
import org.flab.api.domain.category.dto.CategoryResponse;
import org.flab.api.domain.category.dto.SubCategoryResponse;
import org.flab.api.domain.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListResponse> getCategoryList () {
        CategoryList categoryList = categoryService.getAllCategoryList();
        List<CategoryResponse> categoryResponsesList = this.createCategoryResponseList(categoryList);
        CategoryListResponse response = new CategoryListResponse(categoryResponsesList.size(), categoryResponsesList);
        return ResponseEntity.ok(response);
    }

    private List<CategoryResponse> createCategoryResponseList(CategoryList categoryList) {
        Map<Long, CategoryResponse> topCategoryResponseMap = this.createTopCategoryResponseMap(categoryList);
        this.addSubCategoryResponseList(categoryList, topCategoryResponseMap);
        return new ArrayList<>(topCategoryResponseMap.values());
    }

    private Map<Long, CategoryResponse> createTopCategoryResponseMap(CategoryList categoryList) {
        return categoryList.getTopCategoryStream().collect(Collectors.toMap(Category::getId, Category::toResponse));
    }

    private void addSubCategoryResponseList(CategoryList categoryList, Map<Long, CategoryResponse> topCategoryResponseMap) {
        topCategoryResponseMap.forEach((categoryId, categoryResponse) -> {
            List<SubCategoryResponse> subCategoryResponseList = categoryList.getSubCategoryStreamByParentId(categoryId).map(Category::toSubCategoryResponse).toList();
            topCategoryResponseMap.get(categoryId).getSubCategories().addAll(subCategoryResponseList);
        });
    }
}
