package com.example.shoppingpay.response;

import com.example.shoppingpay.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    public RespCustomer(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.customerSurname = customer.getCustomerSurname();
        this.dataDate = customer.getDataDate();
        this.active = customer.getActive();
    }
}
