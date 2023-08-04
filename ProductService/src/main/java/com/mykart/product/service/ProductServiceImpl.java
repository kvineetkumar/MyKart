package com.mykart.product.service;

import com.mykart.product.constant.SortOrder;
import com.mykart.product.entity.Product;
import com.mykart.product.exception.InvalidInputException;
import com.mykart.product.exception.ResourcesNotFoundException;
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
    public List<Product> getProductsByDateOrder(String sortOrder) {
        if (sortOrder.contentEquals(SortOrder.ASC.name()) ||
                sortOrder.contentEquals(SortOrder.DESC.name())
                || sortOrder.isEmpty()) {
            SortOrder order = sortOrder.isEmpty() ? SortOrder.ASC : SortOrder.valueOf(sortOrder);
            List<Product> products = switch (order) {
                case ASC -> productRepository.findAllByOrderByManufacturedDateAsc();
                case DESC -> productRepository.findAllByOrderByManufacturedDateDesc();
            };

            if (products.isEmpty()) throw new ResourcesNotFoundException("");
            else return products;
        } else throw new InvalidInputException("Sort order should be either ASC or DESC");
    }

    @Override
    public List<Product> getProductsById(List<String> productIds) {
        if (!productIds.isEmpty())
            return productRepository.findAllById(productIds);
        else throw new InvalidInputException("Invalid product Id(s) provided.");
    }

    @Override
    public List<Product> searchForProductsByKey(String keyword) {
        List<Product> products = productRepository.findByKeyword(keyword);
        if (products.isEmpty()) throw new ResourcesNotFoundException("");
        else return products;
    }
}