package com.example.shoppingpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "payment")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    private Double amount;
    @CreationTimestamp
    private Date payDate;
    @ColumnDefault(value = "1")
    private Integer isActive;
}
