package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.dto.CategoryDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.CategoryAlreadyExist;
import com.example.fashionblog.exception.CategoryNotFound;
import com.example.fashionblog.exception.PermissionDenied;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.User;
import com.example.fashionblog.model.pageCriteria.CategoryPage;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.service.CategoryService;
import com.example.fashionblog.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final SessionUtil util;

    @Override
    public String addCategory(CategoryDto categoryDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("You are not permitted to perform this operation");
        }

        String name = categoryDto.getName();
        Optional<Category> category = categoryRepository.findByName(name);

        if (category.isPresent()) {
            throw new CategoryAlreadyExist("Category already exist!");
        }
        Category category1 = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(category1);

        return "Category added successfully!";
    }

    @Override
    public Page<Category> fetchCategory(CategoryPage categoryPage) {
        Sort sort = Sort.by(categoryPage.getSortDirection(), categoryPage.getSortBy());
        Pageable pageable = PageRequest.of(categoryPage.getPageNumber(), categoryPage.getPageSize(), sort);
        Page<Category> category = categoryRepository.findAll(pageable);
        return category;
    }

    @Override
    public Category fetchCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFound("Category not found!"));

        return category;
    }

    @Override
    public String updateCategory(Long categoryId, CategoryDto categoryDto) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("You are not an admin!");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFound("Category not found!"));

        if (categoryDto.getName() != null){
            category.setName(categoryDto.getName());
        }

        categoryRepository.save(category);

        return "Category updated successfully!";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Long userId = util.getLoggedInUser();
        User user = util.findUserById(userId);

        if (user.getRole().equals(Role.CUSTOMER)){
            throw new PermissionDenied("You are not an admin");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFound("Category not found!"));

        categoryRepository.delete(category);

        return "Category deleted successfully!";
    }

}
