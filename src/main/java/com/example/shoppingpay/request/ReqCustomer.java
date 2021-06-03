package com.example.shoppingpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.sql.Date;


@Data
@JacksonXmlRootElement
public class ReqCustomer {
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private Date dob;
}
