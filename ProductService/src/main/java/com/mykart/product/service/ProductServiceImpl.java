package com.mykart.product.service;

import com.mykart.product.constant.SortOrder;
import com.mykart.product.entity.Product;
import com.mykart.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByName(String query) {
        return productRepository.findByName(query);
    }

    @Override
    public List<Product> getProductsByDateOrder(SortOrder sortOrder) {
        return switch (sortOrder) {
            case ASC -> productRepository.findAllByOrderByManufacturedDateAsc();
            case DESC -> productRepository.findAllByOrderByManufacturedDateDesc();
        };
    }

    @Override
    public List<Product> getProductsById(List<String> productIds) {
        return productRepository.findAllById(productIds);
    }

    @Override
    public List<Product> searchForProductsByKey(String keyword) {
        return productRepository.findByKeyword(keyword);
    }
}