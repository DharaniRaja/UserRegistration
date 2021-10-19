package com.dharani.homepage.repository;


import com.dharani.homepage.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Integer> {
    void deleteCartByProductId(int id);
    Cart findCartByProductId(int productId);
}
