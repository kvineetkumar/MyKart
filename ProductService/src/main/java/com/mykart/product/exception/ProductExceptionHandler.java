package com.mykart.product.exception;

import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ProductExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The information provided is not valid.")
    @ExceptionHandler(ValidationException.class)
    void handleValidationException() {
        log.debug("ValidationException received...");
    }

    /*@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "The resources which you are looking for are not available.")
    @ExceptionHandler(ResourcesNotFoundException.class)
    void handleResourcesNotFoundException() {
        log.debug("ResourcesNotFoundException received...");
    }*/

    @ExceptionHandler(ResourcesNotFoundException.class)
    void handleResourcesNotFoundException(String message) {
        log.debug("ResourcesNotFoundException received...");
    }

    @ExceptionHandler(InvalidInputException.class)
    void handleInvalidInputException(String message) {
        log.debug("ResourcesNotFoundException received...");
    }
}