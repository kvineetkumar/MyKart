package com.mykart.product.config;

import com.mykart.product.dto.response.ProductResponse;
import com.mykart.product.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableCaching
@Configuration
public class CacheConfig {

    @Autowired
    CacheManager cacheManager;
    @Autowired
    private ProductService productService;

    @Autowired
    private Logger log;

    @PostConstruct
    public void preloadCache() {
        Cache cache = cacheManager.getCache("productsCache");
        if (cache != null) {
            log.debug("Cache initialized...");
            List<ProductResponse> products = productService.getAllProducts();
            products.forEach(product -> cache.put(product.getId(), product));
        } else log.debug("Cache not available!");
    }
}
