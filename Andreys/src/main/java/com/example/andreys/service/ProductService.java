package com.example.andreys.service;

import com.example.andreys.model.dto.CreateProductDTO;
import com.example.andreys.model.dto.ItemDTO;

import java.util.List;

public interface ProductService {
    boolean checkAvailable(CreateProductDTO createProductDTO);

    void register(CreateProductDTO createProductDTO);

    List<ItemDTO> getItems();

    ItemDTO getProduct(String name);

    void removeProduct(String name);
}
