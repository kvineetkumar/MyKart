package com.mykart.product.controller;

import com.mykart.product.constant.SortOrder;
import com.mykart.product.entity.Product;
import com.mykart.product.service.ProductService;
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
        if (!productIds.isEmpty())
            return ResponseEntity.ok(productService.getProductsById(productIds));
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("search")
    ResponseEntity<List<Product>> getProductsByName(@RequestParam("q") String query) {
        return ResponseEntity.ok(productService.getProductsByName(query));
    }

    @GetMapping("sort/date")
    ResponseEntity<List<Product>> getProductsByDateOrder(@RequestParam("order") String order) {
        return ResponseEntity.ok(productService.getProductsByDateOrder(SortOrder.valueOf(order)));
    }
}