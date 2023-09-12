package com.mykart.product.repository;

import com.mykart.product.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockItem, String> {

    List<StockItem> findByQuantityGreaterThan(int quantity);

    default List<StockItem> findAllByAvailability() {
        return findByQuantityGreaterThan(0);
    }

    Optional<StockItem> findByIdAndQuantityGreaterThan(String id, int quantity);
}