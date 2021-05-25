package com.example.shoppingpay.service.serviceImpl;

import com.example.shoppingpay.enums.EnumAvaibleStatus;
import com.example.shoppingpay.exception.ExceptionConstans;
import com.example.shoppingpay.model.Orders;
import com.example.shoppingpay.model.Payment;
import com.example.shoppingpay.repository.OrderDao;
import com.example.shoppingpay.repository.PaymentDao;
import com.example.shoppingpay.request.ReqPayment;
import com.example.shoppingpay.response.RespOrder;
import com.example.shoppingpay.response.RespPayment;
import com.example.shoppingpay.response.RespPaymentList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.OrderService;
import com.example.shoppingpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;


    @Override
    public RespPaymentList getPaymentList() {
        RespPaymentList response = new RespPaymentList();
        try{
            List<RespPayment> respPaymentList = new ArrayList<>();
            List<Payment> payments = paymentDao.findAllByIsActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (payments.isEmpty()){
                response.setRespStatus(new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Customer not found Exception"));
                return response;
            }
            for (Payment payment : payments){
                RespPayment respPayment = getPaymentById(payment.getPaymentId());
                respPaymentList.add(respPayment);
            }
            response.setRespPaymentList(respPaymentList);
            response.setRespStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setRespStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public RespPayment getPaymentById(Long paymentId) {
        RespPayment response = new RespPayment();
        try{
            if (paymentId==null){
                response.setStatus(new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Invalid request Exception"));
                return response;
            }
            Payment payment = paymentDao.findPaymentByPaymentIdAndIsActive(paymentId,EnumAvaibleStatus.ACTIVE.getValue());
            if (payment==null){
                response.setStatus(new RespStatus(ExceptionConstans.PAYMENT_NOT_FOUND, "Payment not found Exception"));
                return response;
            }
            response.setAmount(payment.getAmount());
            RespOrder order = orderService.getOrderById(payment.getOrder().getOrderId());
            response.setRespOrder(order);
            response.setPayDate(payment.getPayDate());
            response.setIsActive(payment.getIsActive());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addPayment(ReqPayment reqPayment) {
        RespStatus status = null;
        try{
             Long  orderId = reqPayment.getOrderId();
             Double amount = reqPayment.getAmount();
             if (orderId==null || amount==null){
                 return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
             }
            Orders order = orderDao.findOrdersByOrderIdAndActive(orderId,EnumAvaibleStatus.ACTIVE.getValue());
             if (order==null){
                 return new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Order not found");
             }
             Payment payment = new Payment();
             payment.setOrder(order);
             payment.setAmount(amount);
             paymentDao.save(payment);
             status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updatePayment(ReqPayment reqPayment) {
        RespStatus status = null;
        try{
            Long id = reqPayment.getPaymentId();
            Long orderId = reqPayment.getOrderId();
            Double amount = reqPayment.getAmount();
            if (id==null || orderId==null){
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Payment payment = paymentDao.findPaymentByPaymentIdAndIsActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (payment==null){
                return new RespStatus(ExceptionConstans.PAYMENT_NOT_FOUND, "Payment not found");
            }
            Orders order = orderDao.findOrdersByOrderIdAndActive(orderId,EnumAvaibleStatus.ACTIVE.getValue());
            if (order==null){
                return new RespStatus(ExceptionConstans.ORDER_NOT_FOUND, "Order not found");
            }
            payment.setAmount(amount);
            payment.setOrder(order);
            paymentDao.save(payment);
            status = RespStatus.getSuccesMessage();

        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal exception");
        }
        return status;
    }

    @Override
    public RespStatus deletePayment(Long paymentId) {
        RespStatus status = null;
        try{
            if (paymentId==null){
                return new RespStatus(ExceptionConstans.INVALID_REQUEST_EXCEPTION, "Ivalid request data");
            }
            Payment payment = paymentDao.findPaymentByPaymentIdAndIsActive(paymentId,EnumAvaibleStatus.ACTIVE.getValue());
            if (payment==null){
                return new RespStatus(ExceptionConstans.PRODUCT_NOT_FOUND, "Paymant not found");
            }
            payment.setIsActive(EnumAvaibleStatus.DEACTIVE.getValue());
            paymentDao.save(payment);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstans.INTERNAL_EXCEPTION, "Internal exception");
        }
        return status;
    }
}
