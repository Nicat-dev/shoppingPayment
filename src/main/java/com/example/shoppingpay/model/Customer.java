package com.example.shoppingpay.model;

import com.example.shoppingpay.request.ReqCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@DynamicInsert
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private Date dob;
    @ColumnDefault(value = "1")
    private Integer active;
    @CreationTimestamp
    private Date dataDate;

    public Customer(ReqCustomer reqCustomer) {
        this.customerId = reqCustomer.getCustomerId();
        this.customerName = reqCustomer.getCustomerName();
        this.customerSurname = reqCustomer.getCustomerSurname();
        this.dob = reqCustomer.getDob();
    }
}
