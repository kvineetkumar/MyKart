package com.mykart.cart.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}