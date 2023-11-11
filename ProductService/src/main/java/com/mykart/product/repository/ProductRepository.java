package com.mykart.product.repository;

import com.mykart.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByNameContainingIgnoreCase(String query);

    default List<Product> findByName(String query) {
        return findByNameContainingIgnoreCase(query);
    }

    List<Product> findAllByOrderByManufacturedDateAsc();

    List<Product> findAllByOrderByManufacturedDateDesc();

    default List<Product> findByKeyword(String keyword) {
        return findAllByKeywordsContainingIgnoreCaseOrNameContainingIgnoreCase(keyword, keyword);
    }

    List<Product> findAllByKeywordsContainingIgnoreCaseOrNameContainingIgnoreCase(@Param("keyword") String keyword, @Param("name") String name);

    Page<Product> findByKeywordsContaining(String keyword, Pageable pageable);
}