package com.catogery_Service.controller;

import com.catogery_Service.Service.CategoryService;
import com.catogery_Service.dto.SaloonDto;
import com.catogery_Service.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/saloon_owner")
public class SaloonCategoryController {

    private final CategoryService categoryService;
    public SaloonCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SaloonDto saloonDto = new SaloonDto();
        saloonDto.setId(1L);
        Category category1 = categoryService.createCategory(category,saloonDto);
        return ResponseEntity.ok(category1);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId")  Long categoryId) throws Exception {
        SaloonDto saloonDto = new SaloonDto();
        saloonDto.setId(1L);
        String category= categoryService.deleteCategoryById(categoryId, saloonDto.getId());
        return ResponseEntity.ok("Category Deleted Successfully!!!");

    }
}
