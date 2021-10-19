package com.dharani.homepage.service;



import com.dharani.homepage.DTO.AddressDTO;

import com.dharani.homepage.model.OrderModel;

import com.dharani.homepage.model.User;

import java.util.List;

public interface OrderService {

    OrderModel addOrder(List<Integer> messages, List<String> messages1);

    int addAddress(User addressDTO,List<Integer> id);
}