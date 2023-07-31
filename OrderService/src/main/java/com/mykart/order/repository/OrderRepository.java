package com.mykart.order.repository;

import com.mykart.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Cart, String> {
}
