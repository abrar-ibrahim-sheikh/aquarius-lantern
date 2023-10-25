package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.vo.CategoryData;

import java.util.List;

public interface CategoryService {

    CategoryData saveCategory(CategoryData category);

    CategoryData getCategoryById(Long categoryId);

    List<CategoryData> getAllCategories();

    CategoryData updateCategory(Long categoryId, CategoryData category);

    void deleteCategoryById(Long categoryId);

}
