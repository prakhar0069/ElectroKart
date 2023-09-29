package com.project.electronic.store.services;

import com.project.electronic.store.dto.AddItemToCartRequest;
import com.project.electronic.store.dto.CartDto;

public interface CartService {

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    void removeItemFromCart(String userId,int cartItem);

    void clearCart(String userId);
    CartDto getCartByUser(String userId);

}
