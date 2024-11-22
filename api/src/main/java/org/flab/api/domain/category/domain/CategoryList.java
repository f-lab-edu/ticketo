package org.flab.api.domain.category.domain;

import java.util.List;

public class CategoryList {

    private final List<Category> categoryList;

    public CategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getTopCategoryList() {
        return categoryList.stream()
                .filter(Category::isTopCategory)
                .toList();
    }

    public List<Category> getSubCategoryListByParentId(long parentId) {
        return categoryList.stream()
                .filter(category -> !category.isTopCategory() && category.getParent().getId() == parentId)
                .toList();
    }
}
