package com.example.shoppingpay.repository;

import com.example.shoppingpay.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product,Long> {
    List<Product> findAllByActive(Integer active);
    Product findProductByProductIdAndActive(Long id,Integer active);
}
