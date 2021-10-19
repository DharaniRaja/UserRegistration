package com.dharani.homepage.repository;

import com.dharani.homepage.model.OrderModel;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderModel, Integer> {
    OrderModel findOrderModelByOrderId(int id);
}