package com.example.fashionblog.service;

import com.example.fashionblog.dto.CategoryDto;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.pageCriteria.CategoryPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    String addCategory(CategoryDto categoryDto);

    Page<Category> fetchCategory(CategoryPage categoryPage);

    String updateCategory(Long categoryId, CategoryDto categoryDto);

    String deleteCategory(Long categoryId);

    Category fetchCategoryById(Long categoryId);
}
