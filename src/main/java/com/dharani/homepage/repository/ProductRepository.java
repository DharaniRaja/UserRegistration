package com.dharani.homepage.repository;

import com.dharani.homepage.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel , Integer> {
    ProductModel findProductModelByProductId(int id);
}
