package com.example.shoppingpay.request;

import com.example.shoppingpay.response.RespStatus;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Date;

@Data
@JacksonXmlRootElement
public class ReqProduct {
    private Long productId;
    private String productName;
    private Float productPrice;
    private Integer active;
}
