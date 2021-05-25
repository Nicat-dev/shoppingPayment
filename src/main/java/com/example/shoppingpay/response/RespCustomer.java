package com.example.shoppingpay.response;

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
public class RespCustomer {
    @JsonProperty(value = "id")
    private Long customerId;
    @JsonProperty(value = "name")
    private String customerName;
    @JsonProperty(value = "surname")
    private String customerSurname;
    @JsonProperty(value = "active")
    private Integer active;
    @JsonProperty(value = "createdAt")
    private Date dataDate;
    private RespStatus status;
}
