package com.example.coffeeshop.service.serviceImpl;

import com.example.coffeeshop.model.dto.CreateOrderDTO;
import com.example.coffeeshop.model.dto.OrderDTO;
import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.enums.CategoryName;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, CategoryRepository categoryRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void register(CreateOrderDTO createOrderDTO) {
        User user = this.userService.getLoggedUser();
        Category category = this.categoryRepository
                .findByName(CategoryName.valueOf(createOrderDTO.getCategory()));
        Order order = new Order(createOrderDTO.getName(),
                createOrderDTO.getPrice(),
                createOrderDTO.getOrderTime(),
                category,
                createOrderDTO.getDescription(),
                user);
        this.orderRepository.save(order);

    }

    @Override
    public List<OrderDTO> getOrderList(CategoryName name) {

       List<Order> orders = this.orderRepository.findAllByCategoryName(name);

        List<OrderDTO> orderList = orders.stream().map(OrderDTO::new)
                .collect(Collectors.toList());

        return orderList;
    }

    @Override
    public void remove(Long id) {
        this.orderRepository.deleteById(id);
    }
}
