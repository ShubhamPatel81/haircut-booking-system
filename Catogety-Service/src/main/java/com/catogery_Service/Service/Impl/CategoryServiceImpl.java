package com.catogery_Service.Service.Impl;

import com.catogery_Service.Repository.CategoryRepositoy;
import com.catogery_Service.Service.CategoryService;
import com.catogery_Service.dto.SaloonDto;
import com.catogery_Service.model.Category;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final  CategoryRepositoy categoryRepositoy;

    public CategoryServiceImpl(CategoryRepositoy categoryRepositoy) {
        this.categoryRepositoy = categoryRepositoy;
    }

    @Override
    public Category createCategory(Category category, SaloonDto saloonDto) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setSaloonId(saloonDto.getId());
        newCategory.setImage(category.getImage());

        return categoryRepositoy.save(newCategory);
    }
    @Override
    public Set<Category> getAllCategoryBySaloon(Long salonId) {
        return categoryRepositoy.findBySaloonId(salonId);
    }
    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepositoy.findById(id).orElse(null );
        if (category ==null){
            throw  new Exception("Catogery not exist with the given id + "+id);
        }
        return category;
    }
    @Override
    public String deleteCategoryById(Long categoryId, Long saloonId) throws Exception {
       Category category= getCategoryById(categoryId);
       if (category.getSaloonId()!= saloonId){
           throw new Exception("You don't have permission to delete this catogery");
       }
        categoryRepositoy.deleteById(categoryId);
        return "Category Deleted SuccessFully "+categoryId;
    }
}
