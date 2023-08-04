package com.mykart.product.controller;

import com.mykart.product.entity.Product;
import com.mykart.product.service.ProductService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{productIds}")
    ResponseEntity<List<Product>> getProductsByIds(@PathVariable("productIds") List<String> productIds) {
        return ResponseEntity.ok(productService.getProductsById(productIds));
    }

    @GetMapping("sort/date")
    ResponseEntity<List<Product>> getProductsByDateOrder(@RequestParam("order") String order) {
        return ResponseEntity.ok(productService.getProductsByDateOrder(order));
    }

    @GetMapping("search")
    ResponseEntity<List<Product>> searchForProducts(@RequestParam("q") @NotNull @NotEmpty String query) {
        return ResponseEntity.ok(productService.searchForProductsByKey(query));
    }
}