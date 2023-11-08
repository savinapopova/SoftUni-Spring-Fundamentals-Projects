package com.example.andreys.repository;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryName category);
}
