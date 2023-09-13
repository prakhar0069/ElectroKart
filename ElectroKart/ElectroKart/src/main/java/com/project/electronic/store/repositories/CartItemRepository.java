package com.project.electronic.store.repositories;

import com.project.electronic.store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
