package com.example.shoppingpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespCustomerList {
    @JsonProperty(value = "CustomerList")
    private List<RespCustomer> respCustomerList;
    private RespStatus status;
}
