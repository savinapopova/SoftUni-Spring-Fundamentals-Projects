package com.example.coffeeshop.service;

import com.example.coffeeshop.model.dto.CreateOrderDTO;
import com.example.coffeeshop.model.dto.OrderDTO;
import com.example.coffeeshop.model.enums.CategoryName;

import java.util.List;

public interface OrderService {
    void register(CreateOrderDTO createOrderDTO);

    List<OrderDTO> getOrderList(CategoryName name);

    void remove(Long id);
}
