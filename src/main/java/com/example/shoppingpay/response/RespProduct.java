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
public class RespProduct {
    @JsonProperty(value = "id")
    private Long respProductId;
    @JsonProperty(value = "name")
    private String respProductName;
    @JsonProperty(value = "CraetedAt")
    private Date respProductDate;
    @JsonProperty(value = "price")
    private Float respProductPrice;
    @JsonProperty("active")
    private Integer respActive;

    private RespStatus status;
}
