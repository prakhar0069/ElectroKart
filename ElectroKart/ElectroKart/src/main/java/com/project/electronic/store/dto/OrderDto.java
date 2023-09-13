package com.project.electronic.store.dto;

import com.project.electronic.store.entities.OrderItem;
import com.project.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class OrderDto {
    private String orderId;
    private String orderStatus="PENDING";
    private String paymentStatus="NOTPAID";
    private int orderAmount;
    private String billingAddress;
    private String billinPhone;
    private String billingName;
    private Date orderedDate=new Date();
    private Date deliveredDate;
    //private UserDto user;
    private List<OrderItemDto> orderItems = new ArrayList<>();


}
