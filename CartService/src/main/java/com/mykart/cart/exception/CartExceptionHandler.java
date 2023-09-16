package com.mykart.cart.exception;

import com.mykart.cart.dto.StockNotAvailableMessage;
import com.mykart.cart.model.Product;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartExceptionHandler {

    @Autowired
    private Logger log;

    @ExceptionHandler(ResourcesNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resources which you are looking for are not available.")
    void handleResourcesNotFoundException(ResourcesNotFoundException exception) {
        log.debug("ResourcesNotFoundException received...");
    }

    @ExceptionHandler(StockNotAvailableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    StockNotAvailableMessage handleStockNotAvailableException(StockNotAvailableException exception) {
        log.debug("StockNotAvailableException received...");
        return new StockNotAvailableMessage(exception.getMessage(), exception.getProducts().stream().map(Product::getId).toList());
    }
}
