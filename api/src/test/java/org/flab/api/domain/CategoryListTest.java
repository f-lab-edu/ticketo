package org.flab.api.domain;

import org.flab.api.domain.category.domain.Category;
import org.flab.api.domain.category.domain.CategoryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CategoryListTest {

    private CategoryList target;
    private final List<Category> categoryEntityList = new ArrayList<>();
    private final long topEventId = 1L;

    @BeforeEach
    public void setupCategoryList() {
        categoryEntityList.add(new Category(topEventId, "상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()));
        categoryEntityList.add(new Category(2L, "상위카테고리2", null, ZonedDateTime.now(), ZonedDateTime.now()));
        categoryEntityList.add(new Category(3L, "하위카테고리1", new Category(topEventId, "상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()), ZonedDateTime.now(), ZonedDateTime.now()));
        categoryEntityList.add(new Category(4L, "하위카테고리2", new Category(topEventId, "상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()), ZonedDateTime.now(), ZonedDateTime.now()));
        categoryEntityList.add(new Category(5L, "하위카테고리3", new Category(2L, "상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()), ZonedDateTime.now(), ZonedDateTime.now()));
        categoryEntityList.add(new Category(6L, "하위카테고리4", new Category(2L, "상위카테고리", null, ZonedDateTime.now(), ZonedDateTime.now()), ZonedDateTime.now(), ZonedDateTime.now()));

        target = new CategoryList(categoryEntityList);
    }

    @Test
    @DisplayName("최상위 카테고리 조회")
    public void getTopCategoryList() {
        // given
        // when
        List<Category> topCategoryList = target.getTopCategoryList();

        // then
        assertThat(topCategoryList.stream()
                .allMatch(Category::isTopCategory))
                .isTrue();
    }

    @Test
    @DisplayName("하위 카테고리 조회")
    public void getSubCategoryList() {
        // given
        // when
        List<Category> subCategoryList = target.getSubCategoryListByParentId(topEventId);

        // then
        assertThat(subCategoryList.stream()
                .allMatch(category -> !category.isTopCategory() && category.getParent().getId() == 1L))
                .isTrue();
    }

}
