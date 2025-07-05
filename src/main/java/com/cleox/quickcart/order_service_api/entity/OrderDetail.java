package com.cleox.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name="order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detail_id", nullable = false,unique = true,length = 80)
    private String detailId;

    @Column(name = "product_id", nullable = false,length = 80)
    private String productId;

    @Column(name = "qty")
    private int qty;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name="customer_order_id")
    private CustomerOrder customerOrder;

}
