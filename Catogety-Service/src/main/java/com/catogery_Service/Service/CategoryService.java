package com.catogery_Service.Service;

import com.catogery_Service.dto.SaloonDto;
import com.catogery_Service.model.Category;

import java.util.Set;

public interface CategoryService {

    Category createCategory(Category category, SaloonDto saloonDto);

    Set<Category> getAllCategoryBySaloon(Long salonId);

    Category getCategoryById(Long id) throws Exception;

    String deleteCategoryById(Long categoryId, Long saloonId) throws Exception;


}
