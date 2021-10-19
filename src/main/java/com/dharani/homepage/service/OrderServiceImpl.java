package com.dharani.homepage.service;



import com.dharani.homepage.DTO.AddressDTO;
import com.dharani.homepage.model.OrderModel;
import com.dharani.homepage.model.User;
import com.dharani.homepage.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderModel addOrder(final List<Integer> uId, final List<String> messages1) {
        final OrderModel orderModel = new OrderModel();
        OrderModel orderModelByUId = orderRepository.findOrderModelByOrderId(uId.get(1));
        orderModelByUId.setPaymentType(messages1.get(0));
        orderModelByUId.setPaymentSuccessfull(true);
        orderRepository.save(orderModelByUId);
        return null;
    }

    @Override
    public int addAddress(User user, List<Integer> id) {
        OrderModel orderModel = new OrderModel();
        int Uid = id.get(0);
        orderModel.setOrderModel(user.getName(), user.getPhoneNo(), user.getDoorNo_StreetName(), user.getDistrict(),
                user.getState(), user.getPincode(), user.getCountry());
        orderModel.setUId(Uid);
        OrderModel save = orderRepository.save(orderModel);

        return save.getOrderId();
    }
}