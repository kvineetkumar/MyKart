package com.mykart.product.controller;

import com.mykart.product.dto.request.ProductRequest;
import com.mykart.product.entity.Product;
import com.mykart.product.service.ProductManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product/manage/")
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productManagementService.createProduct(productRequest);
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    List<Product> createProducts(@RequestBody @Valid List<ProductRequest> productRequest) {
        return productManagementService.createProducts(productRequest);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteProduct(@PathVariable("id") String productId) {
        productManagementService.deleteProduct(productId);
    }

    @DeleteMapping("delete/batch")
    @ResponseStatus(HttpStatus.OK)
    void deleteProducts(@RequestBody @NotEmpty List<String> productIds) {
        productManagementService.deleteProducts(productIds);
    }

    @PatchMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    Product updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
        return productManagementService.updateProduct(id, productRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    Product replaceProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
        return productManagementService.updateProduct(id, productRequest);
    }
}