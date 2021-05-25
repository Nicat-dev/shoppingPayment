package com.example.shoppingpay.service;

import com.example.shoppingpay.request.ReqPayment;
import com.example.shoppingpay.response.RespPayment;
import com.example.shoppingpay.response.RespPaymentList;
import com.example.shoppingpay.response.RespStatus;

public interface PaymentService {
    RespPaymentList getPaymentList();
    RespPayment getPaymentById(Long paymentId);
    RespStatus addPayment(ReqPayment reqPayment);
    RespStatus updatePayment(ReqPayment reqPayment);
    RespStatus deletePayment(Long paymentId);
}
