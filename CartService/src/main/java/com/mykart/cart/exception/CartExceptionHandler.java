package com.mykart.cart.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class CartExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    void handleResourcesNotFoundException(String message) {
        log.debug("ResourcesNotFoundException received...");
    }
}
