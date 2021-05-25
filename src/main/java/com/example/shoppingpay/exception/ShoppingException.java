package com.example.shoppingpay.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShoppingException extends RuntimeException{
    public ShoppingException(Integer code,String message){
        super(message);
    }
}
