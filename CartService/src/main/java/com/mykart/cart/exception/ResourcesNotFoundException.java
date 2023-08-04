package com.mykart.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resources which you are looking for are not available.")
public class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException() {
        super();
    }

    public ResourcesNotFoundException(String message) {
        super(message);
    }
}