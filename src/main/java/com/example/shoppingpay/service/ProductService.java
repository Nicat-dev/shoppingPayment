package com.example.shoppingpay.service;

import com.example.shoppingpay.request.ReqProduct;
import com.example.shoppingpay.response.RespProduct;
import com.example.shoppingpay.response.RespProductList;
import com.example.shoppingpay.response.RespStatus;

public interface ProductService {
    RespProductList getProductList();
    RespProduct getProductById(Long productId);
    RespStatus updateProduct(ReqProduct reqProduct);
    RespStatus addProduct(ReqProduct reqProduct);
    RespStatus deleteProduct(Long productId);
}
