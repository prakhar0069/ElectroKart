package com.project.electronic.store.services;

import com.project.electronic.store.dto.CreateOrderRequest;
import com.project.electronic.store.dto.OrderDto;
import com.project.electronic.store.dto.PageableResponse;

import java.util.List;

public interface OrderService{

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);

    //update order
    OrderDto updateOrder(OrderDto orderDto, String orderId);


}
