package com.cleox.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="customer_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @Column(name = "order_id", nullable = false,unique = true,length = 80)
    private String orderId;

    @Column(name = "user_id", nullable = false,length = 80)
    private String userId;

    @Column(name = "total_amount", nullable = false,precision = 10,scale = 2)
    private double totalAmount;

    @Column(name = "remark", length = 750)
    private String remark;

    @Column(name = "order_date", nullable = false,columnDefinition = "DATETIME")
    private Date orderDate;

    @OneToMany(mappedBy = "customerOrder")
    private Set<OrderDetail> orderDetails=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
}
