package com.example.shoppingpay.request;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqOrder {
    private Long orderId;
    private String orderName;
    private Integer orderQuantity;
    private Long productId;
    private Long customerId;
}
