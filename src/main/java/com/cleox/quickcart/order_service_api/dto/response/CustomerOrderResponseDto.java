package com.cleox.quickcart.order_service_api.dto.response;

import com.cleox.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderResponseDto {
    private String orderId;
    private String userId;
    private BigDecimal totalAmount;
    private Date orderDate;
    private String status;
    private String remark;
    private List<OrderDetailResponseDto> orderDetails;
}
