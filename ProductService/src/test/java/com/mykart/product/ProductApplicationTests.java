package com.mykart.product;

import com.mykart.product.entity.Product;
import com.mykart.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ProductApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void setRandomNetPriceForAllProducts(@Autowired ProductRepository productRepository) {
        List<Product> products = productRepository.findAll();
        products = products.stream().map(product -> {
            double originalPrice = product.getOriginalPrice().doubleValue();
            double netPrice = originalPrice + (originalPrice * (new Random().nextDouble(5.0, 20.0) / 100));
            product.setNetPrice(BigDecimal.valueOf(netPrice));
            return product;
        }).toList();
        productRepository.saveAll(products);
    }
}