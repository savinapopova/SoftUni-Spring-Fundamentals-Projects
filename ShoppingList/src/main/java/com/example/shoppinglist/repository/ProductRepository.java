package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findAllByCategoryName(CategoryName food);
}
