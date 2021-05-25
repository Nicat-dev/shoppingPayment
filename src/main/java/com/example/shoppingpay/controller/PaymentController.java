package com.example.shoppingpay.controller;

import com.example.shoppingpay.request.ReqPayment;
import com.example.shoppingpay.response.RespPayment;
import com.example.shoppingpay.response.RespPaymentList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/getPaymentList")
    public RespPaymentList getPaymentList(){
        return paymentService.getPaymentList();
    }

    @RequestMapping(value ="/getPaymentById/{paymentId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespPayment getPaymentById(@PathVariable("paymentId") Long paymentId){
        return paymentService.getPaymentById(paymentId);
    }
    @PostMapping(value = "/addPayment")
    public RespStatus addPayment(@RequestBody ReqPayment reqPayment){
        return paymentService.addPayment(reqPayment);
    }

    @PostMapping(value = "/updatePayment")
    public RespStatus updatePayment(@RequestBody ReqPayment reqPayment){
        return paymentService.updatePayment(reqPayment);
    }

    @PostMapping(value = "/deletePayment")
    public RespStatus deletePayment(@PathParam("paymentId") Long paymentId){
        return paymentService.deletePayment(paymentId);
    }
}
