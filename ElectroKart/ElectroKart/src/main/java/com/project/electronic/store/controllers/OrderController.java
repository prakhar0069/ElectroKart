package com.project.electronic.store.controllers;

import com.project.electronic.store.dto.ApiResponseMessage;
import com.project.electronic.store.dto.CreateOrderRequest;
import com.project.electronic.store.dto.OrderDto;
import com.project.electronic.store.dto.PageableResponse;
import com.project.electronic.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request){
        OrderDto order = orderService.createOrder(request);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
       ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .status(HttpStatus.OK)
                .message("order is removed !!")
                .success(true)
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId){
        List<OrderDto> ordersOfUser = orderService.getOrdersOfUser(userId);
        return new ResponseEntity<>(ordersOfUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
            @RequestParam(value= "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value= "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value= "sortBy",defaultValue = "OrderedDate",required = false) String sortBy,
            @RequestParam(value= "sortDir",defaultValue = "desc",required = false) String sortDir
    ){
        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public  ResponseEntity updateOrder(@PathVariable String orderId, @RequestBody OrderDto orderDto){
        OrderDto order = orderService.updateOrder(orderDto, orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
