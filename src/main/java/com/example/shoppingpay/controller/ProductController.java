package com.example.shoppingpay.controller;

import com.example.shoppingpay.request.ReqProduct;
import com.example.shoppingpay.response.RespProduct;
import com.example.shoppingpay.response.RespProductList;
import com.example.shoppingpay.response.RespStatus;
import com.example.shoppingpay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getProductList")
    public RespProductList getProductList(){
        return productService.getProductList();
    }

    @RequestMapping(value ="/getProductById/{productId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespProduct getProductById(@PathVariable("productId") Long productId){
        return productService.getProductById(productId);
    }
    @PostMapping(value = "/addProduct")
    public RespStatus addProduct(@RequestBody ReqProduct reqProduct){
        return productService.addProduct(reqProduct);
    }

    @PostMapping(value = "/updateProduct")
    public RespStatus updateProduct(@RequestBody ReqProduct reqProduct){
        return productService.updateProduct(reqProduct);
    }

    @PostMapping(value = "/deleteProduct")
    public RespStatus deleteProduct(@PathParam("productId") Long productId){
        return productService.deleteProduct(productId);
    }
}
