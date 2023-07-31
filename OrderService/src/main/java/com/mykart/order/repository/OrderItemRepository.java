package com.mykart.order.repository;

import com.mykart.order.entity.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<CardItem, String> {
}
