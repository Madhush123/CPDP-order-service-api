package com.cleox.quickcart.order_service_api.service.impl;

import com.cleox.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.cleox.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.cleox.quickcart.order_service_api.entity.CustomerOrder;
import com.cleox.quickcart.order_service_api.entity.OrderDetail;
import com.cleox.quickcart.order_service_api.entity.OrderStatus;
import com.cleox.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.cleox.quickcart.order_service_api.repo.OrderStatusRepo;
import com.cleox.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    
    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;
    
    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setRemark("");
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());

        OrderStatus orderStatus=orderStatusRepo.findByStatus("PENDING").orElseThrow(()->new RuntimeException("Order status not found"));
        customerOrder.setOrderStatus(orderStatus);

        customerOrder.setOrderDetails(requestDto.getOrderDetails().stream().map(e -> createOrderDetail(e, customerOrder)).collect(Collectors.toSet()));

        customerOrderRepo.save(customerOrder);
    }

    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if(requestDto==null){
            return  null;
        }

        return OrderDetail.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(requestDto.getUnitPrice())
                .qty(requestDto.getQty())
                .discount(requestDto.getDiscount())
                .customerOrder(order)
                .build();
    }
}
