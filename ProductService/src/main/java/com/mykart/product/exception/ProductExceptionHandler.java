package com.mykart.product.exception;

import jakarta.validation.ValidationException;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

    @Autowired
    private Logger log;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The information provided is not valid.")
    @ExceptionHandler(ValidationException.class)
    void handleValidationException() {
        log.debug("ValidationException received...");
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    void handleResourcesNotFoundException(String message) {
        log.debug("ResourcesNotFoundException received...");
    }

    @ExceptionHandler(InvalidInputException.class)
    void handleInvalidInputException(String message) {
        log.debug("ResourcesNotFoundException received...");
    }
}