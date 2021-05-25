package com.example.shoppingpay.repository;

import com.example.shoppingpay.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository<Customer,Long> {
    List<Customer> findAllByActive(Integer active);
    Customer findCustomerByCustomerIdAndActive(Long id,Integer active);
}
