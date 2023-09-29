package com.project.electronic.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AddItemToCartRequest {

    private String productId;
    private int quantity;

}
