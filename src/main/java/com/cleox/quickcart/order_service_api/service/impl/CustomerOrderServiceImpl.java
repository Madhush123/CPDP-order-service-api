package com.cleox.quickcart.order_service_api.service.impl;

import com.cleox.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.cleox.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.cleox.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.cleox.quickcart.order_service_api.dto.response.OrderDetailResponseDto;
import com.cleox.quickcart.order_service_api.dto.response.paginate.CustomerOrderPaginateResponseDto;
import com.cleox.quickcart.order_service_api.entity.CustomerOrder;
import com.cleox.quickcart.order_service_api.entity.OrderDetail;
import com.cleox.quickcart.order_service_api.entity.OrderStatus;
import com.cleox.quickcart.order_service_api.exception.EntryNotFoundException;
import com.cleox.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.cleox.quickcart.order_service_api.repo.OrderStatusRepo;
import com.cleox.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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

        OrderStatus orderStatus = orderStatusRepo.findByStatus("PENDING").orElseThrow(() -> new RuntimeException("Order status not found"));
        customerOrder.setOrderStatus(orderStatus);

        customerOrder.setOrderDetails(requestDto.getOrderDetails().stream().map(e -> createOrderDetail(e, customerOrder)).collect(Collectors.toSet()));

        customerOrderRepo.save(customerOrder);
    }

    @Override
    public void updateOrder(CustomerOrderRequestDto requestDto, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrderRepo.save(customerOrder);
    }

    @Override
    public void manageRemark(String remark, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        customerOrder.setRemark(remark);
    }

    @Override
    public void manageStatus(String status, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        OrderStatus orderStatus = orderStatusRepo.findByStatus(status).orElseThrow(() -> new EntryNotFoundException("Order status not found"));
        customerOrder.setOrderStatus(orderStatus);
    }

    @Override
    public CustomerOrderResponseDto findOrderById(String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        return toCustomerOrderResponseDto(customerOrder);


    }

    @Override
    public void deleteOrderById(String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(() -> new RuntimeException(String.format("Order not found with %s", orderId)));
        customerOrderRepo.delete(customerOrder);
    }

    @Override
    public CustomerOrderPaginateResponseDto searchAllOrders(String searchText, int page, int size) {
        return CustomerOrderPaginateResponseDto.builder()
                .count(
                        customerOrderRepo.searchCount(searchText)
                )
                .orderList(
                        customerOrderRepo.searchAll(searchText,PageRequest.of(page,size)).stream().map(this::toCustomerOrderResponseDto).collect(Collectors.toList())
                )
                .build();
    }

    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder) {
        if (customerOrder == null) {

            return null;
        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .remark(customerOrder.getRemark())
                .totalAmount(customerOrder.getTotalAmount())
                .userId(customerOrder.getUserId())
                .orderDetails(
                        customerOrder.getOrderDetails().stream().map(this::toOrderDetailDto).collect(Collectors.toList())
                )
                .status(customerOrder.getOrderStatus().getStatus())
                .build();
    }

    private OrderDetailResponseDto toOrderDetailDto(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        return OrderDetailResponseDto.builder()
                .detailId(orderDetail.getDetailId())
                .productId(orderDetail.getProductId())
                .qty(orderDetail.getQty())
                .unitPrice(orderDetail.getUnitPrice())
                .discount(orderDetail.getDiscount())
                .build();
    }

    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if (requestDto == null) {
            return null;
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
