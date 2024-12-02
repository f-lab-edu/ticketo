package org.flab.api.domain.category.domain;

import java.util.List;
import java.util.stream.Stream;

public class CategoryList {

    private final List<Category> categoryList;

    public CategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Stream<Category> getTopCategoryStream() {
        return categoryList.stream()
                .filter(Category::isTopCategory);
    }

    public Stream<Category> getSubCategoryStreamByParentId(long parentId) {
        return categoryList.stream()
                .filter(category -> !category.isTopCategory() && category.getParent().getId() == parentId);
    }
}
