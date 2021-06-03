package com.example.shoppingpay.model;

import com.example.shoppingpay.request.ReqOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
@DynamicInsert
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderName;
    private Integer orderQuantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ColumnDefault(value = "1")
    private Integer active;
    @CreationTimestamp
    private Date dataDate;

    public Orders(ReqOrder reqOrder) {
        this.orderId = reqOrder.getOrderId();
        this.orderName = reqOrder.getOrderName();
        this.orderQuantity = reqOrder.getOrderQuantity();
    }
}
