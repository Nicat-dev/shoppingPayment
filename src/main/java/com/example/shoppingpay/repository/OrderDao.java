package com.example.shoppingpay.repository;

import com.example.shoppingpay.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Orders,Long> {
    List<Orders> findAllByActive(Integer active);
    Orders findOrdersByOrderIdAndActive(Long id,Integer active);
}
