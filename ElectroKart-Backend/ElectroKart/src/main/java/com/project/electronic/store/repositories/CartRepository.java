package com.project.electronic.store.repositories;

import com.project.electronic.store.entities.Cart;
import com.project.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<Cart> findByUser(User user);

}
