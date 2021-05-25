package com.example.shoppingpay.service;


import com.example.shoppingpay.request.ReqOrder;
import com.example.shoppingpay.request.ReqProduct;
import com.example.shoppingpay.response.RespOrder;
import com.example.shoppingpay.response.RespOrederList;
import com.example.shoppingpay.response.RespStatus;

public interface OrderService {
    RespOrederList getOrderList();
    RespOrder getOrderById(Long orderId);
    RespStatus addOrder(ReqOrder reqOrder);
    RespStatus updateOrder(ReqOrder reqOrder);
    RespStatus deleteOrder(Long orderId);

}
