package com.catogery_Service.Repository;

import com.catogery_Service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface  CategoryRepositoy extends JpaRepository<Category, Long> {

    Set<Category> findBySaloonId(Long saloonId);

}
