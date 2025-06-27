package com.cleox.quickcart.order_service_api.repo;

import com.cleox.quickcart.order_service_api.entity.OrderDetail;
import com.cleox.quickcart.order_service_api.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderStatusRepo extends JpaRepository<OrderStatus,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM order_status WHERE status=?1")
    public Optional<OrderStatus> findByStatus(String status);
}
