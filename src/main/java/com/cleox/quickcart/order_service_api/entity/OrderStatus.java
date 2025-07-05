package com.cleox.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="order_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "status_id", nullable = false,unique = true,length = 80)
    private String statusId;

    @Column(name = "status", nullable = false,length = 80,unique = true)
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<CustomerOrder> customerOrders=new HashSet<>();
}
