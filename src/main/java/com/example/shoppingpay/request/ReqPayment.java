package com.example.shoppingpay.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqPayment {
    @JsonProperty(value = "paymentId")
    private Long paymentId;
    @JsonProperty(value = "orderId")
    private Long  orderId;
    private Double amount;
}
