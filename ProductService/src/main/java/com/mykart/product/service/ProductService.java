package com.mykart.product.service;

import com.mykart.product.constant.SortOrder;
import com.mykart.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByName(String query);

    List<Product> getProductsByDateOrder(SortOrder sortOrder);

    List<Product> getProductsById(List<String> productIds);
}
