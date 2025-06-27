package com.cleox.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="order_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

    @Id
    @Column(name = "status_id", nullable = false,unique = true,length = 80)
    private String statusId;

    @Column(name = "status", nullable = false,length = 80,unique = true)
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<CustomerOrder> customerOrders=new HashSet<>();
}
