package com.cleox.quickcart.order_service_api.service.impl;

import com.cleox.quickcart.order_service_api.entity.OrderStatus;
import com.cleox.quickcart.order_service_api.repo.OrderStatusRepo;
import com.cleox.quickcart.order_service_api.service.OderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OderStatusServiceImpl implements OderStatusService {

    private final OrderStatusRepo orderStatusRepo;
    @Override
    public void initializeStatusList() {
        long count=orderStatusRepo.count();
        if(count==0){
            orderStatusRepo.saveAll(
                    List.of(
                            OrderStatus.builder()
                                    .status("PENDING")
                                    .build(),
                            OrderStatus.builder()
                                    .status("COMPLETED")
                                    .build(),
                            OrderStatus.builder()
                                    .status("REJECTED_BY_USER")
                                    .build(),
                            OrderStatus.builder()
                                    .status("REJECTED_BY_ADMIN")
                                    .build()

                    )
            );
        }
    }
}
