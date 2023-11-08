package com.example.andreys.service.serviceImpl;

import com.example.andreys.model.dto.CreateProductDTO;
import com.example.andreys.model.dto.ItemDTO;
import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.Item;
import com.example.andreys.model.enums.CategoryName;
import com.example.andreys.model.enums.Gender;
import com.example.andreys.repository.CategoryRepository;
import com.example.andreys.repository.ProductRepository;
import com.example.andreys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean checkAvailable(CreateProductDTO createProductDTO) {

        Optional<Item> optionalItem = this.productRepository
                .findByName(createProductDTO.getName());
        if (optionalItem.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public void register(CreateProductDTO createProductDTO) {
        Category category = this.categoryRepository
                .findByName(CategoryName.valueOf(createProductDTO.getCategory()));

        Item item = new Item(createProductDTO.getName(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                category,
                Gender.valueOf(createProductDTO.getGender()));
        this.productRepository.save(item);
    }

    @Override
    public List<ItemDTO> getItems() {

        List<Item> items = this.productRepository.findAll();

       return items.stream().map(ItemDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getProduct(String name) {
        Optional<Item> optionalItem = this.productRepository.findByName(name);
        Item item = optionalItem.get();

        return new ItemDTO(item);
    }

    @Override
    public void removeProduct(String name) {
        this.productRepository.deleteByName(name);
    }
}
