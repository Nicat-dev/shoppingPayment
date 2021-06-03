package com.example.shoppingpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


@Data
@JacksonXmlRootElement
public class ReqProduct {
    private Long productId;
    private String productName;
    private Double productPrice;
}
