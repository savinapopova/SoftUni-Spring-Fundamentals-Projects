package com.example.battleships.repository;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryName categoryName);
}
