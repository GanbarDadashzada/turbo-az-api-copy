package com.carzapi.digital.model.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
