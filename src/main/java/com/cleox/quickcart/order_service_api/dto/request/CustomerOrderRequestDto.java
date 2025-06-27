package com.cleox.quickcart.order_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequestDto {
    private String userId;
    private double totalAmount;
    private Date orderDate;
    private ArrayList<OrderDetailRequestDto> orderDetails;
}
