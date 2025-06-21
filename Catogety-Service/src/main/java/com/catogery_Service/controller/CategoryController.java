package com.catogery_Service.controller;

import com.catogery_Service.Service.CategoryService;
import com.catogery_Service.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    final private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<Set<Category>> getCategoryBySaloon(@PathVariable("saloonId")Long saloonId){
        Set<Category> categories= categoryService.getAllCategoryBySaloon(saloonId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) throws Exception {
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(category);
    }


}
