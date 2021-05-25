package com.example.shoppingpay.controller;

import com.example.shoppingpay.request.ReqCustomer;
import com.example.shoppingpay.response.RespCustomer;
import com.example.shoppingpay.response.RespCustomerList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getCustomerList")
    public RespCustomerList getCustomerList(){
        return customerService.getCustomerList();
    }

    @RequestMapping(value ="/getCustomerById/{customerId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespCustomer getCustomerById(@PathVariable("customerId") Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(value = "/addCustomer")
    public RespStatus addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }

    @PostMapping(value = "/updateCustomer")
    public RespStatus updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }

    @PostMapping(value = "/deleteCustomer")
    public RespStatus deleteStudent(@PathParam("customerId") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

}
