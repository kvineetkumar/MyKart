package com.mykart.cart.exception;

import com.mykart.cart.dto.StockNotAvailableMessage;
import com.mykart.cart.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class CartExceptionHandler {

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
