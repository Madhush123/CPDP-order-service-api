package com.cleox.quickcart.order_service_api.service;

import com.cleox.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.cleox.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.cleox.quickcart.order_service_api.dto.response.paginate.CustomerOrderPaginateResponseDto;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public void updateOrder(CustomerOrderRequestDto requestDto,String orderId);
    public void manageRemark(String remark,String orderId);
    public void manageStatus(String status,String orderId);
    public CustomerOrderResponseDto findOrderById(String orderId);
    public void deleteOrderById(String orderId);
    public CustomerOrderPaginateResponseDto searchAllOrders(String searchText,int page,int size);
}
