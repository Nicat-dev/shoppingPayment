package com.example.shoppingpay.controller;

import com.example.shoppingpay.request.ReqOrder;
import com.example.shoppingpay.response.RespOrder;
import com.example.shoppingpay.response.RespOrederList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getOrderList")
    public RespOrederList getOrderList(){
        return orderService.getOrderList();
    }

    @RequestMapping(value ="/getOrderById/{paymentId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespOrder getOrderById(@PathVariable("paymentId") Long orderId){
        return orderService.getOrderById(orderId);
    }
    @PostMapping(value = "/addOrder")
    public RespStatus addOrder(@RequestBody ReqOrder reqOrder){
        return orderService.addOrder(reqOrder);
    }

    @PostMapping(value = "/updateOrder")
    public RespStatus updateOrder(@RequestBody ReqOrder reqOrder){
        return orderService.updateOrder(reqOrder);
    }

    @PostMapping(value = "/deleteOrder")
    public RespStatus deleteStudent(@PathParam("orderId") Long orderId){
        return orderService.deleteOrder(orderId);
    }
}
