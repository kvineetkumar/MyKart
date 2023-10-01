package com.mykart.product.controller;

import com.mykart.product.dto.response.ProductResponse;
import com.mykart.product.service.ProductService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("stock")
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getAllAvailableProducts() {
        return productService.getAllAvailableProducts();
    }

    @GetMapping("{productIds}")
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getProductsByIds(@PathVariable("productIds") List<String> productIds) {
        return productService.getProductsById(productIds);
    }

    @GetMapping("sort/date")
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getProductsByDateOrder(@RequestParam("order") String order) {
        return productService.getProductsByDateOrder(order);
    }

    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> searchForProducts(@RequestParam("q") @NotNull @NotEmpty String query) {
        return productService.searchForProductsByKey(query);
    }
}