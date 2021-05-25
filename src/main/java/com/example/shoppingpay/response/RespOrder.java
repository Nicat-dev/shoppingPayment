package com.example.shoppingpay.response;

import com.example.shoppingpay.model.Customer;
import com.example.shoppingpay.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespOrder {
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

    private RespStatus status;
}
