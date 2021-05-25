package com.example.shoppingpay.repository;

import com.example.shoppingpay.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends CrudRepository<Payment,Long> {
    List<Payment> findAllByIsActive(Integer active);
    Payment findPaymentByPaymentIdAndIsActive(Long id,Integer active);
}
