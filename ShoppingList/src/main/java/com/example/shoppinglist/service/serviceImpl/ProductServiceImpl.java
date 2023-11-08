package com.example.shoppinglist.service.serviceImpl;

import com.example.shoppinglist.model.dto.CreateProductDTO;
import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.enums.CategoryName;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public boolean productExists(CreateProductDTO createProductDTO) {
        Optional<Product> optionalProduct = this.productRepository.findByName(createProductDTO.getName());
        return optionalProduct.isPresent();

        }

    @Override
    public void register(CreateProductDTO createProductDTO) {

        Category category = this.categoryRepository
                .findByName(CategoryName.valueOf(createProductDTO.getCategory()));


        Product product = new Product(createProductDTO.getName(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                createProductDTO.getBefore(),
                category);
        this.productRepository.save(product);

    }

    @Override
    public List<ProductDTO> getAllFoods() {
        List<Product> foods = this.productRepository.findAllByCategoryName(CategoryName.FOOD);
        return foods.stream().map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllDrinks() {
        List<Product> drinks = this.productRepository.findAllByCategoryName(CategoryName.DRINK);
        return drinks.stream().map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllHousehold() {
        List<Product> household = this.productRepository.findAllByCategoryName(CategoryName.HOUSEHOLD);
        return household.stream().map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllOthers() {
        List<Product> others = this.productRepository.findAllByCategoryName(CategoryName.OTHER);
        return others.stream().map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Product findProduct(Long id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public void remove(Product product) {
        this.productRepository.delete(product);
    }



    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    @Override
    public BigDecimal getTotalSum() {
        List<Product> products = this.productRepository.findAll();

        BigDecimal totalSum = BigDecimal.valueOf(0.00);

        for (Product product : products) {
            totalSum = totalSum.add(product.getPrice());
        }

        return totalSum;
    }
}

