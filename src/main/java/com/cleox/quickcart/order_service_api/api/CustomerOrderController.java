package com.cleox.quickcart.order_service_api.api;

import com.cleox.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.cleox.quickcart.order_service_api.service.CustomerOrderService;
import com.cleox.quickcart.order_service_api.util.StandardResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
@Transactional
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

@Transactional
    @PostMapping("/business/create-order")
    public ResponseEntity<StandardResponseDto> create(@RequestBody CustomerOrderRequestDto request) {
        customerOrderService.createOrder(request);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201,"customer order has been created.",null
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/visitors/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"customer order details.",customerOrderService.findOrderById(id)
                ), HttpStatus.OK
        );
    }

    @PutMapping("/business/update-order/{id}")
    public ResponseEntity<StandardResponseDto> updateOrder(@RequestBody CustomerOrderRequestDto request,@PathVariable String id) {
        customerOrderService.updateOrder(request,id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"customer order hase been updated.",null
                ), HttpStatus.OK
        );
    }

    @PutMapping("/business/update-remark/{id}")
    public ResponseEntity<StandardResponseDto> manageRemark(@RequestParam String remark,@PathVariable String id) {
        customerOrderService.manageRemark(remark,id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201,"customer order remark has been updated.",null
                ), HttpStatus.OK
        );
    }

    @PutMapping("/business/update-status/{id}")
    public ResponseEntity<StandardResponseDto> manageStatus(@RequestParam String status,@PathVariable String id) {
        customerOrderService.manageStatus(status,id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201,"customer order status has been updated.",null
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/business/delete-by-id/{id}")
    public ResponseEntity<StandardResponseDto> manageStatus(@PathVariable String id) {
        customerOrderService.deleteOrderById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204,"customer order has been deleted.",null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitors/search-all")
    public ResponseEntity<StandardResponseDto> manageStatus(@RequestParam String searchText,@RequestParam int page,@RequestParam int size) {
        customerOrderService.searchAllOrders(searchText,page,size);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"customer order list.",customerOrderService.searchAllOrders(searchText,page,size)
                ), HttpStatus.OK
        );
    }
}
