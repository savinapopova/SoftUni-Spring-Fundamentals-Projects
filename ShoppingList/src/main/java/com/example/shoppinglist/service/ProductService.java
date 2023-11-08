package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dto.CreateProductDTO;
import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    boolean productExists(CreateProductDTO createProductDTO);

    void register(CreateProductDTO createProductDTO);

    List<ProductDTO> getAllFoods();

    List<ProductDTO> getAllDrinks();

    List<ProductDTO> getAllHousehold();

    List<ProductDTO> getAllOthers();

    Product findProduct(Long id);

    void remove(Product product);


    void deleteAll();

    BigDecimal getTotalSum();
}
