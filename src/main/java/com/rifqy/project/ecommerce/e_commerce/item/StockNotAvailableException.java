package com.rifqy.project.ecommerce.e_commerce.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StockNotAvailableException extends RuntimeException {

    public StockNotAvailableException(String message) {
        super(message);
    }
}
