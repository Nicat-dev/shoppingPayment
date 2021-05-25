package com.example.shoppingpay.response;

import com.example.shoppingpay.model.Orders;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.sql.Date;

@Data
@JacksonXmlRootElement
public class RespPayment {
    @JsonProperty(value = "order")
    private RespOrder respOrder;
    private Double amount;
    private Date payDate;
    private Integer isActive;
    private RespStatus status;
}
