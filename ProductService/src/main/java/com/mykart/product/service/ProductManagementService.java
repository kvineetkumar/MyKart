package com.mykart.product.service;

import com.mykart.product.dto.request.ProductRequest;
import com.mykart.product.entity.Product;
import com.mykart.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Service
public class ProductManagementService {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public Product createProduct(ProductRequest productRequest) {
        return createProducts(Collections.singletonList(productRequest)).get(0);
    }

    public List<Product> createProducts(List<ProductRequest> productRequests) {
        final ModelMapper modelMapper = new ModelMapper();
        var products = productRequests.stream().map(productRequest -> {
            modelMapper.addMappings(new PropertyMap<ProductRequest, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getKeywords());
                }
            });
            Product product = modelMapper.map(productRequest, Product.class);
            product.setKeywords(productRequest.getKeywords());
            return product;
        }).toList();
        return productRepository.saveAll(products);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public void deleteProducts(List<String> productIds) {
        productRepository.deleteAllById(productIds);
    }
}
