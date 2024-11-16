package com.rifqy.project.ecommerce.e_commerce.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ItemAlreadyExistException extends RuntimeException {

    public ItemAlreadyExistException(String message) {
        super(message);
    }
}
