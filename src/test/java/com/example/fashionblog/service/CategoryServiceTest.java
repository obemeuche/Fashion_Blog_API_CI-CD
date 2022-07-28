package com.example.fashionblog.service;

import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.CategoryNotFound;
import com.example.fashionblog.exception.PermissionDenied;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.utils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SessionUtil util;

    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Category category =
                Category.builder()
                        .id(1L)
                        .name("Shoes")
                        .build();

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));

    }

    @Test
    @DisplayName("Get Category by Id")
    void fetchCategoryById() {
        Long id = 1L;
        Category found = categoryService.fetchCategoryById(id);

        assertEquals(id, found.getId());

    }
}