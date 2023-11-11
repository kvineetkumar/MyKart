package com.mykart.product.controller;

import com.mykart.product.dto.response.ProductResponse;
import com.mykart.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "List of all products in the catalog")
    List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("stock")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List of all available products in the catalog")
    List<ProductResponse> getAllAvailableProducts() {
        return productService.getAllAvailableProducts();
    }

    @GetMapping("{productIds}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List of products based on their ids")
    List<ProductResponse> getProductsByIds(@PathVariable("productIds") List<String> productIds) {
        return productService.getProductsById(productIds);
    }

    @GetMapping("sort/date")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List of sorted products based on name or manufactured date")
    List<ProductResponse> getProductsByDateOrder(@RequestParam("order") String order) {
        return productService.getProductsByDateOrder(order);
    }

    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search products with pagination using keyword")
    List<ProductResponse> searchForProducts(@RequestParam(value = "q", required = false, defaultValue = "") String query,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        return productService.searchForProductsByKey(query, page, size);
    }
}