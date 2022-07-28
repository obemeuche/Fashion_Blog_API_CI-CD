package com.example.fashionblog.controller;

import com.example.fashionblog.dto.CategoryDto;
import com.example.fashionblog.model.Category;
import com.example.fashionblog.model.pageCriteria.CategoryPage;
import com.example.fashionblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/add-category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }

    @GetMapping("/all-category")
    public ResponseEntity<Page<Category>> fetchCategory (CategoryPage categoryPage){
        return ResponseEntity.ok(categoryService.fetchCategory(categoryPage));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> fetchCategoryById(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.fetchCategoryById(categoryId));
    }

    @PatchMapping("/admin/update-category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("/admin/delete-category/{id}")
    public  ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
