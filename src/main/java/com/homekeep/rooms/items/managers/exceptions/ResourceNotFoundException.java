package com.homekeep.rooms.items.managers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends Throwable {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
