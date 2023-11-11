package com.mykart.product.service;

import com.mykart.product.constant.SortOrder;
import com.mykart.product.dto.response.ProductResponse;
import com.mykart.product.entity.Product;
import com.mykart.product.entity.StockItem;
import com.mykart.product.exception.InvalidInputException;
import com.mykart.product.exception.ResourcesNotFoundException;
import com.mykart.product.repository.ProductRepository;
import com.mykart.product.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(value = "productsCache")
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ResourcesNotFoundException();
        return mapToProductResponse(products);
    }

    public List<ProductResponse> getAllAvailableProducts() {
        List<StockItem> stockItems = stockRepository.findAllByAvailability();
        List<Product> products = productRepository.findAllById(stockItems.stream()
                .map(StockItem::getId)
                .toList());

        return mapToProductResponse(products);
    }

    public List<ProductResponse> getProductsByDateOrder(String sortOrder) {
        if (sortOrder.contentEquals(SortOrder.ASC.name()) ||
                sortOrder.contentEquals(SortOrder.DESC.name())
                || sortOrder.isEmpty()) {
            SortOrder order = sortOrder.isEmpty() ? SortOrder.ASC : SortOrder.valueOf(sortOrder);
            List<Product> products = switch (order) {
                case ASC -> productRepository.findAllByOrderByManufacturedDateAsc();
                case DESC -> productRepository.findAllByOrderByManufacturedDateDesc();
            };

            if (products.isEmpty()) throw new ResourcesNotFoundException("");
            else return mapToProductResponse(products);
        } else throw new InvalidInputException("Sort order should be either ASC or DESC");
    }

    public List<ProductResponse> getProductsById(List<String> productIds) {
        if (!productIds.isEmpty())
            return mapToProductResponse(productRepository.findAllById(productIds));
        else throw new InvalidInputException("Invalid product Id(s) provided.");
    }

    public List<ProductResponse> searchForProductsByKey(String keyword, int page, int size) {
        if (size > 0) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Product> productsPage = productRepository.findByKeywordsContaining(keyword, pageRequest);
            var products = productsPage.getContent();
            return mapToProductResponse(products);
        } else {
            var products = productRepository.findByKeyword(keyword);
            return mapToProductResponse(products);
        }
    }

    public boolean checkIfProductHasStock(String productId) {
        return stockRepository.findByIdAndQuantityGreaterThan(productId, 0).isPresent();
    }

    private List<ProductResponse> mapToProductResponse(List<Product> products) {
        if (products.isEmpty()) return Collections.emptyList();
        List<StockItem> stockItems = stockRepository.findAllById(products.stream().map(Product::getId).toList());
        List<ProductResponse> productResponses = new ArrayList<>();
        products.forEach(product -> {
            ProductResponse productResponse = new ProductResponse();
            modelMapper.map(product, productResponse);
            productResponse.setAvailable(stockItems.stream()
                    .anyMatch(stockItem -> stockItem.getId()
                            .contentEquals(product.getId())));
            productResponses.add(productResponse);
        });
        return productResponses;
    }
}