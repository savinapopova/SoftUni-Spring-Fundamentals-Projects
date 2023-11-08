package com.example.andreys.repository;

import com.example.andreys.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);

    void deleteByName(String name);
}
