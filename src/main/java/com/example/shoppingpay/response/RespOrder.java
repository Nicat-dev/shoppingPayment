package com.example.shoppingpay.response;

import com.example.shoppingpay.model.Customer;
import com.example.shoppingpay.model.Orders;
import com.example.shoppingpay.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespOrder {
    @JsonProperty(value = "id")
    private Long respOrderId;
    @JsonProperty(value = "name")
    private String respOrderName;
    @JsonProperty(value = "quantity")
    private Integer respOrderQuantity;
    @JsonProperty(value = "product")
    private RespProduct respProduct;
    @JsonProperty(value = "customer")
    private RespCustomer respCustomer;
    @JsonProperty("active")
    private Integer respActive;
    @JsonProperty(value = "date")
    private Date respDataDate;
    private RespStatus status;

    public RespOrder(Orders order) {
        this.respOrderId = order.getOrderId();
        this.respOrderName = order.getOrderName();
        this.respOrderQuantity = order.getOrderQuantity();
        this.respDataDate = order.getDataDate();
        this.respActive = order.getActive();
    }
}
