package com.project.electronic.store.dto;

import com.project.electronic.store.entities.Order;
import com.project.electronic.store.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderItemDto {
    private int orderItemId;
    private int quantity;
    private int totalPrice;
    private ProductDto product;

}
