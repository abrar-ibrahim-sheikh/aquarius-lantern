package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.dto.CategoryData;
import com.scribblesphere.aquariuslantern.entity.Category;
import com.scribblesphere.aquariuslantern.exception.ResourceNotFoundException;
import com.scribblesphere.aquariuslantern.repository.CategoryRepository;
import com.scribblesphere.aquariuslantern.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryData saveCategory(CategoryData category) {
        Category newCategory = categoryRepository.save(dataToCategory(category));
        return categoryToData(newCategory);
    }

    @Override
    public CategoryData getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId )));
        return categoryToData(category);
    }

    @Override
    public List<CategoryData> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::categoryToData)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryData updateCategory(Long categoryId, CategoryData category) {
        Category updatedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        updatedCategory.setTitle(category.getTitle());
        updatedCategory.setDescription(category.getDescription());
        return categoryToData(categoryRepository.save(updatedCategory));
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        if (categoryId != null)
            categoryRepository.deleteById(categoryId);
    }

    private Category dataToCategory(CategoryData data) {
         return modelMapper.map(data, Category.class);
    }

    private CategoryData categoryToData(Category category) {
        return modelMapper.map(category, CategoryData.class);
    }
}
