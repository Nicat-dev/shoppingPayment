package com.example.shoppingpay.service;

import com.example.shoppingpay.request.ReqCustomer;
import com.example.shoppingpay.response.RespCustomer;
import com.example.shoppingpay.response.RespCustomerList;
import com.example.shoppingpay.response.RespStatus;

public interface CustomerService {
    RespCustomerList getCustomerList();
    RespCustomer getCustomerById(Long id);
    RespStatus addCustomer(ReqCustomer reqCustomer);
    RespStatus updateCustomer(ReqCustomer reqCustomer);
    RespStatus deleteCustomer(Long customerId);
}
